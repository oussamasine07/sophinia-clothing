import {Component, EventEmitter, inject, OnInit, Output} from '@angular/core';
import {FabricService} from '../../../../../core/services/fabric/fabric-service';
import { FormsModule } from '@angular/forms';
import {DesignInterface} from '../../../../../models/interfaces/design-interface';
import {fabricFormType} from '../../../../../models/types/fabricFormType';
import {NgClass, NgIf} from '@angular/common';

@Component({
  selector: 'app-fabric-create',
  imports: [
    FormsModule, NgIf, NgClass
  ],
  templateUrl: './fabric-create.html',
  styleUrl: './fabric-create.scss'
})
export class FabricCreate implements OnInit {

  fabricService: FabricService = inject( FabricService )

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

  @Output() emitCreatedFabric = new EventEmitter();


  selectedFile: File | null = null;
  onFileChange(e: Event) {
    const inputFile = e.target as HTMLInputElement;
    if (inputFile.files?.length) {
      this.selectedFile = inputFile.files[0];
    }
  }

  // form submit
  fabricFormObj: fabricFormType = {
    name : "",
    description: "",
    price: 0
  }
  onCreateFabricSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.fabricFormObj.name);
    formData.append("description", this.fabricFormObj.description);
    formData.append("price", this.fabricFormObj.price.toString());

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    this.fabricService.createFabric(formData).subscribe({
      next: (d: DesignInterface) => {
        this.emitCreatedFabric.emit(d)
        this.onCloseClick()
        this.fabricFormObj = {
          name: "",
          description: "",
          price: 0
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
