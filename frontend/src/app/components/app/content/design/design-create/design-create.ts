import {Component, inject} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {designFormType} from '../../../../../models/types/designFormType';
import {DesignService} from '../../../../../services/design/design-service';
import {DesignInterface} from '../../../../../models/interfaces/design-interface';

@Component({
  selector: 'app-design-create',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './design-create.html',
  styleUrl: './design-create.css'
})
export class DesignCreate {

  designService: DesignService = inject(DesignService)

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

  formObj: designFormType = {
    name : ""
  }


  onDesignSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.formObj.name);

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    this.designService.createDesign(formData).subscribe({
      next: (res: DesignInterface) => {
        this.formObj = {
          name: ""
        }
        this.selectedFile = null;
      },
      error: (err) => {
        console.log(err)
      }
    })

  }


}

















