import {Component, inject, Input, OnInit} from '@angular/core';
import {NgFor, NgIf} from '@angular/common';
import {DecorationInterface} from '../../../../../models/interfaces/decoration-interface';
import {DecorationService} from '../../../../../services/decoration/decoration-service';
import {DecorationCreate} from '../decoration-create/decoration-create';
import {DecorationUpdate} from '../decoration-update/decoration-update';
import {Popup} from '../../../partials/popup/popup';

@Component({
  selector: 'app-decoration-list',
  imports: [
    NgFor, NgIf,
    DecorationCreate, DecorationUpdate, Popup
  ],
  templateUrl: './decoration-list.html',
  styleUrl: './decoration-list.scss'
})
export class DecorationList implements OnInit {
  decorations: DecorationInterface[] = [];

  decorationService: DecorationService = inject(DecorationService);

  ngOnInit () {
    this.decorationService.getDecorations().subscribe({
      next: (decos: DecorationInterface[]) => {
        this.decorations = decos
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

  addNewDecoration (decoration: DecorationInterface) {
    this.decorations.push( decoration );
  }

  @Input() currentDecoration: DecorationInterface | null = null
  showUpdateModal = false;
  openUpdateModal (decoration: DecorationInterface) {
    this.showUpdateModal = true;
    this.currentDecoration = decoration;
  }
  closeUpdateModal () {
    this.showUpdateModal = false;
  }
  updateDecoration (decoration: DecorationInterface) {
    this.decorations = this.decorations.map((d: DecorationInterface) => d.id == decoration.id ? decoration : d);
  }

  @Input() currentDeleteType: string = "";
  showDeleteModal = false;
  openDeleteModal ( d: DecorationInterface ) {
    this.showDeleteModal = true;
    this.currentDecoration = d;
    this.currentDeleteType = "decoration"
  }
  closeDeleteModal () {
    this.showDeleteModal = false;
    this.currentDecoration = null;
  }
  deleteModal ( d: DecorationInterface ) {
    this.decorations = this.decorations.filter( des => des.id != d.id)
    this.closeDeleteModal()
  }


}
