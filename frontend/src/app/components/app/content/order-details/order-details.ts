import {Component, inject, OnInit} from '@angular/core';
import {OrderService} from '../../../../services/order/order-service';
import {OrderDetailsInterface} from '../../../../models/interfaces/order-details-interface';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-order-details',
  imports: [],
  templateUrl: './order-details.html',
  styleUrl: './order-details.scss'
})
export class OrderDetails implements OnInit {

  orderService: OrderService = inject( OrderService );
  orderDetails: OrderDetailsInterface | null = null;
  orderId!: number;
  route: ActivatedRoute = inject( ActivatedRoute );

  ngOnInit () {

    this.orderId = Number(this.route.snapshot.paramMap.get("id"));
    this.orderService.getOrderDetails( this.orderId ).subscribe({
      next: (orderDetails: OrderDetailsInterface) => {
        this.orderDetails = orderDetails;

        console.log( this.orderDetails );
      }
    })
  }


}
