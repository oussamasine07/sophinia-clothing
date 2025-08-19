import {Component, EventEmitter, inject, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {DesignService} from '../../../../../services/design/design-service';
import {designFormType} from '../../../../../models/types/designFormType';
import {DesignInterface} from '../../../../../models/interfaces/design-interface';
import {ActivatedRoute, Router} from '@angular/router';
import {NgClass, NgIf} from '@angular/common';

@Component({
  selector: 'app-design-update',
  imports: [
    ReactiveFormsModule,
    FormsModule,
    NgClass,
    NgIf
  ],
  templateUrl: './design-update.html',
  styleUrl: './design-update.css'
})
export class DesignUpdate implements OnInit {
  designService: DesignService = inject(DesignService)
  router: Router = inject(Router)

  animate: boolean = false;
  ngOnInit (){
    setTimeout(() => {
      this.animate = true
    }, 10)

    this.designFormObj = {
      name: this.currentDesign?.name || ""
    }
  }

  fieldErrors: Record<string, string | string[]> = {}

  @Input() currentDesign: DesignInterface | null = null;

  @Output() close = new EventEmitter();
  onCloseClick () {
    this.animate = false;
    setTimeout(() => {
      this.close.emit();
    }, 300)
  }

  @Output() emitUpdateDesign = new EventEmitter();

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

  designFormObj: designFormType = {
    name : ""
  }

  onUpdateDesignSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.designFormObj.name);

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    this.designService.updateDesign(formData, this.currentDesign?.id).subscribe({
      next: (d: DesignInterface) => {
        this.emitUpdateDesign.emit( d )
        this.designFormObj = {
          name: ""
        }
        this.selectedFile = null;
        this.onCloseClick()
      },
      error: (err) => {
        console.log(err)
      }
    })

  }

}
