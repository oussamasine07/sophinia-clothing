import {Component, EventEmitter, inject, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, FormsModule} from '@angular/forms';
import {DecorationService} from '../../../../../services/decoration/decoration-service';
import {decorationFormType} from '../../../../../models/types/decorationFormType';
import {DecorationInterface} from '../../../../../models/interfaces/decoration-interface';
import {NgClass, NgIf} from '@angular/common';

@Component({
  selector: 'app-decoration-create',
  imports: [
    FormsModule,
    NgClass,
    NgIf
  ],
  templateUrl: './decoration-create.html',
  styleUrl: './decoration-create.css'
})
export class DecorationCreate implements OnInit {

  decorationService: DecorationService = inject(DecorationService);

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

  @Output() emitCreatedDecoration = new EventEmitter();


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
  decorationFormObj: decorationFormType = {
    name : ""
  }
  onCreateDecorationSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.decorationFormObj.name);

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    this.decorationService.createDecoration(formData).subscribe({
      next: (d: DecorationInterface) => {
        this.emitCreatedDecoration.emit(d)
        this.onCloseClick()
        this.decorationFormObj = {
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
