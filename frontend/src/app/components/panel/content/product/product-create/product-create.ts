import {AfterViewInit, Component, ElementRef, EventEmitter, inject, OnInit, Output, ViewChild} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {DesignInterface} from '../../../../../models/interfaces/design-interface';
import {ProductService} from '../../../../../services/product/product-service';
import {productFromType} from '../../../../../models/types/productFormType';
import {NgClass, NgFor, NgIf} from '@angular/common';
import {MeasurementFieldService} from '../../../../../services/measurement-field/measurement-field-service';
import {MeasurementFieldInterface} from '../../../../../models/interfaces/measurement-field-interface';
import Choices from 'choices.js';
import {ClothingTypeInterface} from '../../../../../models/interfaces/clothing-type-interface';
import {ClothingTypeService} from '../../../../../services/clothing-type/clothing-type-service';

@Component({
  selector: 'app-product-create',
  imports: [
    FormsModule, NgIf, NgClass, NgFor
  ],
  templateUrl: './product-create.html',
  styleUrl: './product-create.scss'
})
export class ProductCreate implements OnInit, AfterViewInit {

  productService: ProductService = inject( ProductService );
  measurementFieldService: MeasurementFieldService = inject( MeasurementFieldService );
  clothingTypeService: ClothingTypeService = inject( ClothingTypeService );

  clothingTypes: ClothingTypeInterface[] = [];
  measurementFields: MeasurementFieldInterface[] = [];
  private choicesInstance: Choices | null = null;

  @ViewChild('measurementFieldsSelect') set citiesSelectSetter(el: ElementRef) {
    if (el && this.measurementFields.length > 0 && !this.choicesInstance) {
      this.choicesInstance = new Choices(el.nativeElement, {
        removeItemButton: true,
        placeholderValue: 'Select measurement fields',
        shouldSort: false,
      });
    }
  }

  ngAfterViewInit(): void {
    this.measurementFieldService.getMeasurementFields().subscribe({
      next: (measurementFields: MeasurementFieldInterface[]) => {
        this.measurementFields = measurementFields;
      },
      error: e => {
        console.log(e)
      }
    })

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

  animate: boolean = false;
  ngOnInit (){
    setTimeout(() => {
      this.animate = true
    }, 10)
  }

  fieldErrors: Record<string, string | string[]> = {}

  @Output() close = new EventEmitter();
  onCloseClick () {
    this.animate = false;
    setTimeout(() => {
      this.close.emit();
    }, 300)
  }

  @Output() emitCreatedProduct = new EventEmitter();


  selectedFile: File | null = null;
  onFileChange(e: Event) {
    const inputFile = e.target as HTMLInputElement;
    if (inputFile.files?.length) {
      this.selectedFile = inputFile.files[0];
    }
  }

  // form submit
  productFormObj: productFromType = {
    name: '',
    description: '',
    clothing_type: null,
    measurements_fields_ids: null,
    measurement_fields: []
  }
  onCreateProductSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.productFormObj.name);
    formData.append("description", this.productFormObj.description);
    formData.append("clothing_type", String(this.productFormObj.clothing_type ?? ''));
    this.productFormObj.measurements_fields_ids?.forEach(id => {
      formData.append("measurements_fields_ids", String(id));
    });
    this.productFormObj.measurement_fields?.forEach((field, index) => {
      formData.append(`measurement_fields[${index}].name`, field.name);
      // formData.append(`measurement_fields[${index}].value`, field.value);
    });

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    this.productService.createProduct(formData).subscribe({
      next: (d: DesignInterface) => {
        this.emitCreatedProduct.emit(d)
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
        console.log( err )
        this.fieldErrors = err.error;
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

}
