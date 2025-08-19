import {Component, EventEmitter, inject, Input, OnInit, Output} from '@angular/core';
import {NgClass} from '@angular/common';
import {DesignService} from '../../../../services/design/design-service';

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

  ngOnInit () {
    setTimeout(() => {
      this.animate = true
    }, 10)
  }

  animate = false

  @Input() current: any | null = null;
  @Output() confiremDelete = new EventEmitter();
  @Output() cancelDelete = new EventEmitter();

  onCancelDeleteClick () {
    this.animate = false;
    setTimeout(() => {
      this.cancelDelete.emit();
    }, 300)
  }

  onConfirmDeleteClick () {
    this.designService.deleteDesign( this.current?.id ).subscribe({
      next: (res: any) => {
        this.confiremDelete.emit(res);
      }
    })
  }
}



























