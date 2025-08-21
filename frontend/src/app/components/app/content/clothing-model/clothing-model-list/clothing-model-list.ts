import {Component, inject, OnInit} from '@angular/core';
import {ClothingModelService} from '../../../../../services/clothing-model/clothing-model-service';
import {ClothingTypeInterface} from '../../../../../models/interfaces/clothing-type-interface';
import {ClothingModelInterface} from '../../../../../models/interfaces/clothing-model-interface';

@Component({
  selector: 'app-clothing-model-list',
  imports: [],
  templateUrl: './clothing-model-list.html',
  styleUrl: './clothing-model-list.css'
})
export class ClothingModelList implements OnInit {

  clothingModelService: ClothingModelService = inject( ClothingModelService )
  clothingModels: ClothingTypeInterface[] = []

  ngOnInit () {
    this.clothingModelService.getClothingModels().subscribe({
      next: (cm: ClothingModelInterface[]) => {
        this.clothingModels = cm;
        console.log( this.clothingModels )
      }
    })
  }
}
