import {Component, EventEmitter, inject, Input, OnInit, Output} from '@angular/core';
import {NgClass} from '@angular/common';
import {DesignService} from '../../../../services/design/design-service';
import {DecorationService} from '../../../../services/decoration/decoration-service';
import {ClothingTypeService} from '../../../../services/clothing-type/clothing-type-service';
import {ClothingModelService} from '../../../../services/clothing-model/clothing-model-service';

@Component({
  selector: 'app-popup',
  imports: [
    NgClass
  ],
  templateUrl: './popup.html',
  styleUrl: './popup.css'
})
export class Popup implements OnInit{

  designService: DesignService = inject( DesignService );
  decortionService: DecorationService = inject( DecorationService );
  clothingTypeService: ClothingTypeService = inject( ClothingTypeService );
  clothingModelService: ClothingModelService = inject( ClothingModelService )

  ngOnInit () {
    setTimeout(() => {
      this.animate = true
    }, 10)
  }

  animate = false

  @Input() current: any | null = null;
  @Input() deleteType: string = "";
  @Output() confiremDelete = new EventEmitter();
  @Output() cancelDelete = new EventEmitter();

  onCancelDeleteClick () {
    this.animate = false;
    setTimeout(() => {
      this.cancelDelete.emit();
    }, 300)
  }

  onConfirmDeleteClick () {
    switch ( this.deleteType ) {
      case "design":
        this.designService.deleteDesign( this.current?.id ).subscribe({
          next: (res: any) => {
            this.confiremDelete.emit(res);
          }
        })
        break;
      case "decoration":
        this.decortionService.deleteDecoration( this.current?.id ).subscribe({
          next: (res: any) => {
            this.confiremDelete.emit(res);
          }
        })
        break;
      case "clothing-type":
        this.clothingTypeService.deleteClothingType( this.current?.id ).subscribe({
          next: (res: any) => {
            this.confiremDelete.emit(res);
          }
        })
        break;
      case "clothing-model":
        this.clothingModelService.deleteClothingModel( this.current?.id ).subscribe({
          next: (res: any) => {
            this.confiremDelete.emit(res);
          }
        })
        break;
    }
  }
}



























