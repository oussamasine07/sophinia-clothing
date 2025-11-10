import {Component, EventEmitter, inject, OnInit, Output} from '@angular/core';
import { FormsModule} from '@angular/forms'
import {ClothingModelService} from '../../../../../core/services/clothing-model/clothing-model-service';
import {clothingModelFormType} from '../../../../../models/types/clothingModelFormType';
import {ClothingModelInterface} from '../../../../../models/interfaces/clothing-model-interface';
import {NgClass, NgIf} from '@angular/common';

@Component({
  selector: 'app-clothing-model-create',
  imports: [
    FormsModule, NgClass, NgIf
  ],
  templateUrl: './clothing-model-create.html',
  styleUrl: './clothing-model-create.scss'
})
export class ClothingModelCreate implements OnInit {

  clothingModelService: ClothingModelService = inject( ClothingModelService )

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

  @Output() emitCreatedClothingModel = new EventEmitter();


  selectedFile: File | null = null;
  onFileChange(e: Event) {
    const inputFile = e.target as HTMLInputElement;
    if (inputFile.files?.length) {
      this.selectedFile = inputFile.files[0];
    }
  }

  // form submit
  clothingModelFormObj: clothingModelFormType = {
    name : ""
  }
  onCreateClothingModelSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.clothingModelFormObj.name);

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    this.clothingModelService.createClothingModel(formData).subscribe({
      next: (cm: ClothingModelInterface) => {
        this.emitCreatedClothingModel.emit( cm )
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
