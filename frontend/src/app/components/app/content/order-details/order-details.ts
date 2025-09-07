import {Component, inject, OnInit} from '@angular/core';
import {OrderService} from '../../../../services/order/order-service';
import {OrderDetailsInterface} from '../../../../models/interfaces/order-details-interface';
import {ActivatedRoute} from '@angular/router';
import {FabricInterface} from '../../../../models/interfaces/fabric-interface';
import {MeasurementModal} from '../measurement-modal/measurement-modal';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-order-details',
  imports: [
    MeasurementModal, NgIf
  ],
  templateUrl: './order-details.html',
  styleUrl: './order-details.scss'
})
export class OrderDetails implements OnInit {

  orderService: OrderService = inject( OrderService );
  orderDetails: OrderDetailsInterface | null = null;
  orderId!: number;
  route: ActivatedRoute = inject( ActivatedRoute );
  measurmentFields!: any;
  measurementSetId!: any;

  ngOnInit () {

    this.orderId = Number(this.route.snapshot.paramMap.get("id"));
    this.orderService.getOrderDetails( this.orderId ).subscribe({
      next: (orderDetails: OrderDetailsInterface) => {
        this.orderDetails = orderDetails;
        this.measurmentFields = this.orderDetails?.order?.product?.fields
        this.measurementSetId = this.orderDetails?.order?.measurementSet;
        console.log(this.measurementSetId)
      }
    })
  }

  showMeasurementModal = false;
  openMeasurementModal () {
    this.showMeasurementModal = true;
  }
  closeMeasurementModal () {
    this.showMeasurementModal = false;
  }


}



























