import {Component, EventEmitter, inject, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {designFormType} from '../../../../../models/types/designFormType';
import {DesignService} from '../../../../../services/design/design-service';
import {DesignInterface} from '../../../../../models/interfaces/design-interface';
import {NgClass, NgIf} from '@angular/common';

@Component({
  selector: 'app-design-create',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    NgClass,
    NgIf
  ],
  templateUrl: './design-create.html',
  styleUrl: './design-create.scss'
})
export class DesignCreate implements OnInit {

  designService: DesignService = inject(DesignService)

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

  @Output() emitCreatedDesign = new EventEmitter();


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
  designFormObj: designFormType = {
    name : ""
  }
  onCreateDesignSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.designFormObj.name);

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    this.designService.createDesign(formData).subscribe({
      next: (d: DesignInterface) => {
        this.emitCreatedDesign.emit(d)
        this.onCloseClick()
        this.designFormObj = {
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

















