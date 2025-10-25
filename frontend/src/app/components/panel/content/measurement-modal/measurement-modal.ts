import {Component, EventEmitter, inject, Input, OnInit, Output, SimpleChange} from '@angular/core';
import { FormsModule } from '@angular/forms';
import {designFormType} from '../../../../models/types/designFormType';
import {DesignInterface} from '../../../../models/interfaces/design-interface';
import {NgClass, NgIf} from '@angular/common';
import {OrderService} from '../../../../services/order/order-service';

@Component({
  selector: 'app-measurement-modal',
  imports: [
    FormsModule,
    NgIf, NgClass
  ],
  templateUrl: './measurement-modal.html',
  styleUrl: './measurement-modal.scss'
})
export class MeasurementModal implements OnInit{

  orderService: OrderService = inject(OrderService);

  animate: boolean = false;
  ngOnInit (){

    setTimeout(() => {
      this.animate = true
    }, 10)

    this.fields.forEach((f: { measurementId: string | number; }) => {
      // @ts-ignore
      this.measurementValues[f.measurementId] = {
        measurementSetId: this.measurementSet,
        measurementFieldId: f.measurementId,
        value: null
      };
    });
  }

  @Input() measurementSet!: any;

  fieldErrors: Record<string, string | string[]> = {}

  @Output() close = new EventEmitter();
  onCloseClick () {
    this.animate = false;
    setTimeout(() => {
      this.close.emit();
    }, 300)
  }

  @Output() emitCreatedMeasurments = new EventEmitter();

  @Input() fields!: any;
  // form submit
  measurementValues: {
    [key: number]: {
      measurementSetId: number,
      measurementFieldId: number,
      value: number | null
    }
  } = {};

  onCreateMeasurementsSubmit (form: FormsModule) {

    const payload = {
      measurementValues: Object.values(this.measurementValues)
    };

    this.orderService.setMeasures(payload).subscribe({
      next: (res: any) => {
        console.log( res )
        this.onCloseClick();
      },
      error: (err) => {
        console.log( err );
      }
    })

  }
}
