import {Component, inject, Input, OnInit} from '@angular/core';
import {ClothingModelService} from '../../../../../services/clothing-model/clothing-model-service';
import {ClothingTypeInterface} from '../../../../../models/interfaces/clothing-type-interface';
import {ClothingModelInterface} from '../../../../../models/interfaces/clothing-model-interface';
import {ClothingTypeCreate} from '../../clothing-type/clothing-type-create/clothing-type-create';
import {ClothingTypeUpdate} from '../../clothing-type/clothing-type-update/clothing-type-update';
import {NgForOf, NgIf} from '@angular/common';
import {Popup} from '../../../partials/popup/popup';
import {ClothingModelCreate} from '../clothing-model-create/clothing-model-create';
import {ClothingModelUpdate} from '../clothing-model-update/clothing-model-update';

@Component({
  selector: 'app-clothing-model-list',
  imports: [
    NgForOf, NgIf,
    ClothingModelCreate, ClothingModelUpdate, Popup
  ],
  templateUrl: './clothing-model-list.html',
  styleUrl: './clothing-model-list.scss'
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


  showCreateModal = false;
  openCreateModal () {
    this.showCreateModal = true;
  }
  closeCreateModal () {
    this.showCreateModal = false
  }

  addNewClothingModel (cm: ClothingModelInterface) {
    this.clothingModels.push( cm );
  }

  @Input() currentClothingModel: ClothingModelInterface | null = null
  showUpdateModal = false;
  openUpdateModal (cm: ClothingModelInterface) {
    this.showUpdateModal = true;
    this.currentClothingModel = cm;
  }
  closeUpdateModal () {
    this.showUpdateModal = false;
  }
  updateClothingModel (cm: ClothingModelInterface) {
    this.clothingModels = this.clothingModels
                              .map((clothingModel: ClothingModelInterface) => clothingModel.id == cm.id ? cm : clothingModel);
  }

  @Input() currentDeleteType: string = "";
  showDeleteModal = false;
  openDeleteModal ( cm: ClothingModelInterface ) {
    this.showDeleteModal = true;
    this.currentClothingModel = cm;
    this.currentDeleteType = "clothing-model";
  }
  closeDeleteModal () {
    this.showDeleteModal = false;
    this.currentClothingModel = null;
  }
  deleteModal ( cm: ClothingModelInterface ) {
    this.clothingModels = this.clothingModels.filter( clothingModel => clothingModel.id != cm.id)
    this.closeDeleteModal()
  }
}
