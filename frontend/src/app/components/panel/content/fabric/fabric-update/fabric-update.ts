import {Component, EventEmitter, inject, Input, Output} from '@angular/core';
import {FormControl, FormGroup, FormsModule} from '@angular/forms';
import {NgClass, NgIf} from '@angular/common';
import {FabricService} from '../../../../../core/services/fabric/fabric-service';
import {fabricFormType} from '../../../../../models/types/fabricFormType';
import {FabricInterface} from '../../../../../models/interfaces/fabric-interface';

@Component({
  selector: 'app-fabric-update',
  imports: [
    FormsModule,
    NgClass,
    NgIf
  ],
  templateUrl: './fabric-update.html',
  styleUrl: './fabric-update.scss'
})
export class FabricUpdate {

  fabricService: FabricService = inject( FabricService )

  animate: boolean = false;
  ngOnInit (){
    setTimeout(() => {
      this.animate = true
    }, 10)

    this.fabricFormObj = {
      name: this.current?.name || "",
      description: this.current?.description || "",
      price: this.current?.price || 0
    }
  }

  fieldErrors: Record<string, string | string[]> = {}

  @Input() current: FabricInterface | null = null;

  @Output() close = new EventEmitter();
  onCloseClick () {
    this.animate = false;
    setTimeout(() => {
      this.close.emit();
    }, 300)
  }

  @Output() emitUpdateFabric = new EventEmitter();


  selectedFile: File | null = null;

  onFileChange(e: Event) {
    const inputFile = e.target as HTMLInputElement;
    if (inputFile.files?.length) {
      this.selectedFile = inputFile.files[0];
    }
  }

  fabricFormObj: fabricFormType = {
    name : "",
    description: "",
    price: 0
  }

  onUpdateFabricSubmit (form: FormsModule) {

    const formData = new FormData();
    formData.append("name", this.fabricFormObj.name);
    formData.append("description", this.fabricFormObj.description);
    formData.append("price", this.fabricFormObj.price.toString());

    if (this.selectedFile) {
      formData.append("image", this.selectedFile)
    }

    this.fabricService.updateFabric(formData, this.current?.id).subscribe({
      next: (f: FabricInterface) => {
        this.emitUpdateFabric.emit( f )
        this.fabricFormObj = {
          name: "",
          description: "",
          price: 0
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
