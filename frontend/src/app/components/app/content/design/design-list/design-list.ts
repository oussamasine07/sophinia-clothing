import {Component, inject, OnInit, Input} from '@angular/core';
import {RouterLink} from '@angular/router';
import {DesignService} from '../../../../../services/design/design-service';
import {DesignInterface} from '../../../../../models/interfaces/design-interface';
import {NgFor, NgIf} from '@angular/common';
import {DesignCreate} from '../design-create/design-create';
import {DesignUpdate} from '../design-update/design-update';

@Component({
  selector: 'app-design-list',
  imports: [
    RouterLink,
    NgFor,
    NgIf,
    DesignCreate,
    DesignUpdate
  ],
  templateUrl: './design-list.html',
  styleUrl: './design-list.css'
})
export class DesignList implements OnInit{

  designs: DesignInterface[] = []

  designService: DesignService = inject( DesignService );

  ngOnInit () {
    this.designService.getDesigns().subscribe({
      next: (d: DesignInterface[]) => {
        this.designs = d;
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

  addNewDesign (design: DesignInterface) {
    this.designs.push( design );
  }

  @Input() currentUpdateDesign: DesignInterface | null = null
  showUpdateModal = false;
  openUpdateModal (design: DesignInterface) {
    this.showUpdateModal = true;
    this.currentUpdateDesign = design;
  }
  closeUpdateModal () {
    this.showUpdateModal = false;
  }
  updateDesign (design: DesignInterface) {
    this.designs = this.designs.map((d: DesignInterface) => d.id == design.id ? design : d);
  }


}














