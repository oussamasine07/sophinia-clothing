import {Component, inject, Input, OnInit} from '@angular/core';
import {NgFor, NgIf} from '@angular/common';
import {DecorationInterface} from '../../../../../models/interfaces/decoration-interface';
import {DecorationService} from '../../../../../services/decoration/decoration-service';

@Component({
  selector: 'app-decoration-list',
  imports: [
    NgFor,
    NgIf,
  ],
  templateUrl: './decoration-list.html',
  styleUrl: './decoration-list.css'
})
export class DecorationList implements OnInit {
  decorations: DecorationInterface[] = [];

  decorationService: DecorationService = inject(DecorationService);

  ngOnInit () {
    this.decorationService.getDecorations().subscribe({
      next: (decos: DecorationInterface[]) => {
        this.decorations = decos
        console.log( this.decorations )
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

  addNewDesign (decoration: DecorationInterface) {
    this.decorations.push( decoration );
  }

  @Input() currentUpdateDesign: DecorationInterface | null = null
  showUpdateModal = false;
  openUpdateModal (decoration: DecorationInterface) {
    this.showUpdateModal = true;
    this.currentUpdateDesign = decoration;
  }
  closeUpdateModal () {
    this.showUpdateModal = false;
  }
  updateDesign (decoration: DecorationInterface) {
    this.decorations = this.decorations.map((d: DecorationInterface) => d.id == decoration.id ? decoration : d);
  }


  showDeleteModal = false;
  openDeleteModal ( d: DecorationInterface ) {
    this.showDeleteModal = true;
    this.currentUpdateDesign = d;
  }
  closeDeleteModal () {
    this.showDeleteModal = false;
    this.currentUpdateDesign = null;
  }
  deleteModal ( d: DecorationInterface ) {
    this.decorations = this.decorations.filter( des => des.id != d.id)
    console.log( d )
    console.log( this.decorations )
    this.closeDeleteModal()
  }


}
