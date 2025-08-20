import {Component, EventEmitter, inject, Input, OnInit, Output} from '@angular/core';
import {NgClass} from '@angular/common';
import {DesignService} from '../../../../services/design/design-service';
import {DecorationService} from '../../../../services/decoration/decoration-service';

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
            console.log("decoration deleted")
            this.confiremDelete.emit(res);
          }
        })
        break;
    }
  }
}



























