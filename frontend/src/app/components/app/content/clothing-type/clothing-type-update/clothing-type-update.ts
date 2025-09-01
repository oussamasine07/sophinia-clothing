import {Component, EventEmitter, inject, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, FormsModule} from '@angular/forms';
import {NgClass, NgIf} from '@angular/common';
import {ClothingTypeService} from '../../../../../services/clothing-type/clothing-type-service';
import {clothingTypeFormType} from '../../../../../models/types/clothingTypeFormType';
import {DesignInterface} from '../../../../../models/interfaces/design-interface';
import {ClothingTypeInterface} from '../../../../../models/interfaces/clothing-type-interface';

@Component({
  selector: 'app-clothing-type-update',
  imports: [
    FormsModule,
    NgIf,
    NgClass
  ],
  templateUrl: './clothing-type-update.html',
  styleUrl: './clothing-type-update.scss'
})
export class ClothingTypeUpdate implements OnInit{

  clothningTypeService: ClothingTypeService = inject(ClothingTypeService);

  animate: boolean = false;
  ngOnInit (){
    setTimeout(() => {
      this.animate = true
    }, 10)

    this.clothingTypeFormObj = {
      name: this.current?.name || ""
    }
  }

  fieldErrors: Record<string, string | string[]> = {}

  @Output() close = new EventEmitter();
  onCloseClick () {
    this.animate = false;
    setTimeout(() => {
      this.close.emit();
    }, 300)
  }

  @Output() emitUpdatedClothingType = new EventEmitter();


  selectedFile: File | null = null;
  onFileChange(e: Event) {
    const inputFile = e.target as HTMLInputElement;
    if (inputFile.files?.length) {
      this.selectedFile = inputFile.files[0];
    }
  }

  @Input() current: ClothingTypeInterface | null = null;

  // form submit
  clothingTypeFormObj: clothingTypeFormType = {
    name : ""
  }
  onUpdateClothingTypeSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.clothingTypeFormObj.name);

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    this.clothningTypeService.updateClothingType(formData, this.current?.id).subscribe({
      next: (ct: ClothingTypeInterface) => {
        this.emitUpdatedClothingType.emit( ct )
        this.onCloseClick()
        this.clothingTypeFormObj = {
          name: ""
        }
        this.selectedFile = null;
      },
      error: (err) => {
        console.log( err )
        this.fieldErrors = err.error;
      }
    })

  }

}
