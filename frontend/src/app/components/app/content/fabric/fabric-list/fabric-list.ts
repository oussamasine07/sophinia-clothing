import {Component, inject, Input, OnInit} from '@angular/core';
import {FabricService} from '../../../../../services/fabric/fabric-service';
import {FabricInterface} from '../../../../../models/interfaces/fabric-interface';
import {NgForOf, NgIf} from '@angular/common';
import {Popup} from '../../../partials/popup/popup';
import {FabricCreate} from '../fabric-create/fabric-create';
import {FabricUpdate} from '../fabric-update/fabric-update';

@Component({
  selector: 'app-fabric-list',
  imports: [
    NgForOf, NgIf,
    FabricCreate, FabricUpdate
  ],
  templateUrl: './fabric-list.html',
  styleUrl: './fabric-list.css'
})
export class FabricList implements OnInit {

  fabricService: FabricService = inject( FabricService )

  fabrics: FabricInterface[] = [];

  ngOnInit () {
    this.fabricService.getFabrics().subscribe({
      next: (f: FabricInterface[]) => {
        this.fabrics = f;
        console.log( this.fabrics )
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

  addNewFabric (f: FabricInterface) {
    this.fabrics.push( f );
  }

  @Input() currentFabric: FabricInterface | null = null
  showUpdateModal = false;
  openUpdateModal (f: FabricInterface) {
    this.showUpdateModal = true;
    this.currentFabric = f;
  }
  closeUpdateModal () {
    this.showUpdateModal = false;
  }
  updateFabric (fabric: FabricInterface) {
    this.fabrics = this.fabrics.map((f: FabricInterface) => f.id == fabric.id ? fabric : f);
  }

  @Input() currentDeleteType: string = "";
  showDeleteModal = false;
  openDeleteModal ( f: FabricInterface ) {
    this.showDeleteModal = true;
    this.currentFabric = f;
    this.currentDeleteType = "fabric";
  }
  closeDeleteModal () {
    this.showDeleteModal = false;
    this.currentFabric = null;
  }
  deleteModal ( fabric: FabricInterface ) {
    this.fabrics = this.fabrics.filter( f => f.id != fabric.id)
    this.closeDeleteModal()
  }


}































