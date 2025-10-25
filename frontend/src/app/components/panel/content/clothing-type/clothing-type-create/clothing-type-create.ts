import {Component, EventEmitter, inject, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, FormsModule} from '@angular/forms';
import {DesignInterface} from '../../../../../models/interfaces/design-interface';
import {ClothingTypeService} from '../../../../../services/clothing-type/clothing-type-service';
import {clothingTypeFormType} from '../../../../../models/types/clothingTypeFormType';
import {NgClass, NgIf} from '@angular/common';

@Component({
  selector: 'app-clothing-type-create',
  imports: [
    FormsModule,
    NgIf,
    NgClass
  ],
  templateUrl: './clothing-type-create.html',
  styleUrl: './clothing-type-create.scss'
})
export class ClothingTypeCreate implements OnInit {

  clothningTypeService: ClothingTypeService = inject(ClothingTypeService);

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

  @Output() emitCreatedClothingType = new EventEmitter();


  // file change
  formGroup = new FormGroup({
    file: new FormControl(null)
  });
  selectedFile: File | null = null;
  onFileChange(e: Event) {
    const inputFile = e.target as HTMLInputElement;
    if (inputFile.files?.length) {
      this.selectedFile = inputFile.files[0];
    }
  }

  // form submit
  clothingTypeFormObj: clothingTypeFormType = {
    name : ""
  }
  onCreateClothingTypeSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.clothingTypeFormObj.name);

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    this.clothningTypeService.createClothingType(formData).subscribe({
      next: (d: DesignInterface) => {
        this.emitCreatedClothingType.emit(d)
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
