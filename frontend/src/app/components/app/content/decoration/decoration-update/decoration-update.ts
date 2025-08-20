import {Component, EventEmitter, inject, Input, OnInit, Output} from '@angular/core';
import {DecorationService} from '../../../../../services/decoration/decoration-service';
import {FormControl, FormGroup, FormsModule} from '@angular/forms';
import {decorationFormType} from '../../../../../models/types/decorationFormType';
import {DecorationInterface} from '../../../../../models/interfaces/decoration-interface';
import {NgClass, NgIf} from '@angular/common';

@Component({
  selector: 'app-decoration-update',
  imports: [
    FormsModule,
    NgClass,
    NgIf
  ],
  templateUrl: './decoration-update.html',
  styleUrl: './decoration-update.css'
})
export class DecorationUpdate implements OnInit {

  decorationService: DecorationService = inject(DecorationService);

  animate: boolean = false;
  ngOnInit (){
    setTimeout(() => {
      this.animate = true
    }, 10)

    this.decorationFormObj = {
      name: this.currentDecoration?.name || ""
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

  @Output() emitUpdatedDecoration = new EventEmitter();
  @Input() currentDecoration: DecorationInterface | null = null;

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
  onUpdateDecorationSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.decorationFormObj.name);

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    this.decorationService.updateDecoration(formData, this.currentDecoration?.id).subscribe({
      next: (d: DecorationInterface) => {
        this.emitUpdatedDecoration.emit(d)
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
