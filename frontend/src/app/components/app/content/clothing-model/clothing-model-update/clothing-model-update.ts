import {Component, EventEmitter, inject, Input, OnInit, Output} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {clothingModelFormType} from '../../../../../models/types/clothingModelFormType';
import {ClothingModelInterface} from '../../../../../models/interfaces/clothing-model-interface';
import {ClothingModelService} from '../../../../../services/clothing-model/clothing-model-service';
import {NgClass, NgIf} from '@angular/common';

@Component({
  selector: 'app-clothing-model-update',
  imports: [
    FormsModule, NgIf, NgClass
  ],
  templateUrl: './clothing-model-update.html',
  styleUrl: './clothing-model-update.scss'
})
export class ClothingModelUpdate implements OnInit {

  clothingModelService: ClothingModelService = inject( ClothingModelService );

  animate: boolean = false;
  ngOnInit (){
    setTimeout(() => {
      this.animate = true
    }, 10)

    this.clothingModelFormObj = {
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

  @Output() emitUpdatedClothingModel = new EventEmitter();


  selectedFile: File | null = null;
  onFileChange(e: Event) {
    const inputFile = e.target as HTMLInputElement;
    if (inputFile.files?.length) {
      this.selectedFile = inputFile.files[0];
    }
  }

  @Input() current: ClothingModelInterface | null = null;

  // form submit
  clothingModelFormObj: clothingModelFormType = {
    name : ""
  }
  onUpdateClothingModelSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.clothingModelFormObj.name);

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    this.clothingModelService.updateClothingModel(formData, this.current?.id).subscribe({
      next: (cm: ClothingModelInterface) => {
        this.emitUpdatedClothingModel.emit( cm )
        this.onCloseClick()
        this.clothingModelFormObj = {
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
