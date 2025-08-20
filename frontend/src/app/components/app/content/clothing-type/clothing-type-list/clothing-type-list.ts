import {Component, inject, Input, OnInit} from '@angular/core';
import {ClothingTypeService} from '../../../../../services/clothing-type/clothing-type-service';
import {ClothingTypeInterface} from '../../../../../models/interfaces/clothing-type-interface';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-clothing-type-list',
  imports: [
    NgForOf
  ],
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


  showCreateModal = false;
  openCreateModal () {
    this.showCreateModal = true;
  }
  closeCreateModal () {
    this.showCreateModal = false
  }

  addNewClothingType (design: ClothingTypeInterface) {
    this.clothingTypes.push( design );
  }

  @Input() currentClothingType: ClothingTypeInterface | null = null
  showUpdateModal = false;
  openUpdateModal (design: ClothingTypeInterface) {
    this.showUpdateModal = true;
    this.currentClothingType = design;
  }
  closeUpdateModal () {
    this.showUpdateModal = false;
  }
  updateClothingType (design: ClothingTypeInterface) {
    this.clothingTypes = this.clothingTypes.map((d: ClothingTypeInterface) => d.id == design.id ? design : d);
  }

  @Input() currentDeleteType: string = "";
  showDeleteModal = false;
  openDeleteModal ( d: ClothingTypeInterface ) {
    this.showDeleteModal = true;
    this.currentClothingType = d;
    this.currentDeleteType = "clothing-type";
  }
  closeDeleteModal () {
    this.showDeleteModal = false;
    this.currentClothingType = null;
  }
  deleteModal ( d: ClothingTypeInterface ) {
    this.clothingTypes = this.clothingTypes.filter( ct => ct.id != d.id)
    console.log( d )
    console.log( this.clothingTypes )
    this.closeDeleteModal()
  }

}
