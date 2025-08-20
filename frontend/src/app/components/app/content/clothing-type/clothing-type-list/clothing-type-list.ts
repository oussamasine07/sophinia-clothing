import {Component, inject, OnInit} from '@angular/core';
import {ClothingTypeService} from '../../../../../services/clothing-type/clothing-type-service';
import {ClothingTypeInterface} from '../../../../../models/interfaces/clothing-type-interface';

@Component({
  selector: 'app-clothing-type-list',
  imports: [],
  templateUrl: './clothing-type-list.html',
  styleUrl: './clothing-type-list.css'
})
export class ClothingTypeList implements OnInit {

  clothingTypeService: ClothingTypeService = inject( ClothingTypeService );

  clothingTypes: ClothingTypeInterface[] = []

  ngOnInit() {
    this.clothingTypeService.getClothingTypes().subscribe({
      next: (ct: ClothingTypeInterface[]) => {
        this.clothingTypes = ct
      }
    })
  }


}
