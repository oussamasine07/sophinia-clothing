import {Component, inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {DesignService} from '../../../../../services/design/design-service';
import {designFormType} from '../../../../../models/types/designFormType';
import {DesignInterface} from '../../../../../models/interfaces/design-interface';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-design-update',
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './design-update.html',
  styleUrl: './design-update.css'
})
export class DesignUpdate implements OnInit {
  designService: DesignService = inject(DesignService)
  router: Router = inject(Router)
  route: ActivatedRoute = inject( ActivatedRoute )



  ngOnInit () {
    const id: string | null = this.route.snapshot.paramMap.get('id');
    console.log( id );
  }

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

  onUpdateDesignSubmit (form: FormsModule) {

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
