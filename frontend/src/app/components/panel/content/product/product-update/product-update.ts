import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  inject,
  Input, OnChanges,
  OnInit,
  Output,
  ViewChild
} from '@angular/core';
import {ProductService} from '../../../../../core/services/product/product-service';
import {FabricInterface} from '../../../../../models/interfaces/fabric-interface';
import {fabricFormType} from '../../../../../models/types/fabricFormType';
import {FormsModule} from '@angular/forms';
import {ProductInterface} from '../../../../../models/interfaces/product-interface';
import {productFromType} from '../../../../../models/types/productFormType';
import {NgClass, NgForOf, NgIf} from '@angular/common';
import {MeasurementFieldService} from '../../../../../core/services/measurement-field/measurement-field-service';
import {ClothingTypeService} from '../../../../../core/services/clothing-type/clothing-type-service';
import {ClothingTypeInterface} from '../../../../../models/interfaces/clothing-type-interface';
import {MeasurementFieldInterface} from '../../../../../models/interfaces/measurement-field-interface';
import Choices from 'choices.js';

@Component({
  selector: 'app-product-update',
  imports: [
    FormsModule,
    NgForOf,
    NgIf,
    NgClass
  ],
  templateUrl: './product-update.html',
  styleUrl: './product-update.scss'
})
export class ProductUpdate implements OnInit, AfterViewInit, OnChanges {

  productService: ProductService = inject( ProductService );
  measurementFieldService: MeasurementFieldService = inject( MeasurementFieldService );
  clothingTypeService: ClothingTypeService = inject( ClothingTypeService );

  clothingTypes: ClothingTypeInterface[] = [];
  measurementFields: MeasurementFieldInterface[] = [];
  private choicesInstance: Choices | null = null;

  @ViewChild('measurementFieldsSelect') set measurementFieldsSelectSetter(el: ElementRef) {
    if (el && this.measurementFields.length > 0 && !this.choicesInstance) {
      this.choicesInstance = new Choices(el.nativeElement, {
        removeItemButton: true,
        placeholderValue: 'Select measurement fields',
        shouldSort: false,
      });
    }
  }

  ngAfterViewInit(): void {

    const selectEl = this.measurementFieldsSelectSetter?.nativeElement;
    if (selectEl) {
      this.choicesInstance = new Choices(selectEl, { removeItemButton: true });
      this.tryBuildChoices();
    }

    this.measurementFieldService.getMeasurementFields().subscribe({
      next: (measurementFields: MeasurementFieldInterface[]) => {
        this.measurementFields = measurementFields;

      },
      error: e => {
        console.log(e);
      }
    });

    this.clothingTypeService.getClothingTypes().subscribe({
      next: (ct: ClothingTypeInterface[]) => {
        this.clothingTypes = ct;
        console.log( this.clothingTypes )
      },
      error: e => {
        console.log(e)
      }
    })

  }

  ngOnChanges(): void {
    console.log('ngOnChanges triggered');
    this.tryBuildChoices();
  }

  private tryBuildChoices(): void {
    if (!this.choicesInstance) {
      console.warn('Choices instance not yet initialized, retrying...');
      setTimeout(() => this.tryBuildChoices(), 50); // retry in 50ms
      return;
    }

    if (this.current?.productMeasurementFields && this.measurementFields.length > 0 ) {
      console.log('Building choices...');
      this.choicesInstance!.clearStore();
      this.choicesInstance!.setChoices(
        this.measurementFields.map(mf => ({
          value: mf.id ?? '',
          label: mf.name ?? '',
          selected: this.current!.productMeasurementFields!.some(cmf => cmf.id === mf.id)
        })),
        'value',
        'label',
        false
      );
    }
  }


  animate: boolean = false;
  ngOnInit (){
    setTimeout(() => {
      this.animate = true
    }, 10)

    this.productFormObj = {
      name: this.current?.name || '',
      description: this.current?.description || '',
      clothing_type: this.current?.clothingType?.id || null,
      // error is here
      measurements_fields_ids: this.current?.productMeasurementFields
        ?.map(mField => mField.id)
        .filter((id): id is number => id != null) || null,
      measurement_fields: []
    }


  }

  fieldErrors: Record<string, string | string[]> = {}

  @Input() current: ProductInterface | null = null;

  @Output() close = new EventEmitter();
  onCloseClick () {
    this.animate = false;
    setTimeout(() => {
      this.close.emit();
    }, 300)
  }

  @Output() emitUpdateProduct = new EventEmitter();


  selectedFile: File | null = null;

  onFileChange(e: Event) {
    const inputFile = e.target as HTMLInputElement;
    if (inputFile.files?.length) {
      this.selectedFile = inputFile.files[0];
    }
  }

  productFormObj: productFromType = {
    name: '',
    description: '',
    clothing_type: null,
    measurements_fields_ids: null,
    measurement_fields: []
  }

  onUpdateProductSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.productFormObj.name);
    formData.append("description", this.productFormObj.description);
    formData.append("clothing_type", String(this.productFormObj.clothing_type ?? ''));
    this.productFormObj.measurements_fields_ids?.forEach(id => {
      formData.append("measurements_fields_ids", String(id));
    });
    this.productFormObj.measurement_fields?.forEach((field, index) => {
      formData.append(`measurement_fields[${index}].name`, field.name);
    });

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    console.log( this.productFormObj )

    this.productService.updateProduct(formData, this.current?.id).subscribe({
      next: (p: ProductInterface) => {
        this.emitUpdateProduct.emit( p )
        this.onCloseClick()
        this.selectedFile = null;
        this.productFormObj = {
          name: '',
          description: '',
          clothing_type: null,
          measurements_fields_ids: null,
          measurement_fields: null
        }
      },
      error: (err) => {
        console.log(err)
      }
    })

  }

  customMeasure: string = ""
  addCustomMeaserement () {
    if ( this.customMeasure != "" ) {
      const measurement = {
        name: this.customMeasure
      }

      this.productFormObj.measurement_fields?.push(measurement)
      this.customMeasure = ""
    }
  }

  removeCustomMeaserement ( i: number ) {
    this.productFormObj.measurement_fields?.splice(i, 1);
  }

  isSelected(id: number | null | undefined): boolean {
    return this.current?.productMeasurementFields?.some(cmf => cmf.id === id) || false;
  }

}
