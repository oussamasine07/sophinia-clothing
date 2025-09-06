import {Component, inject, OnInit} from '@angular/core';
import {OrderService} from '../../../../services/order/order-service';
import {OrderWithClientInterface} from '../../../../models/interfaces/order-with-client-interface';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-order',
  imports: [
    NgClass
  ],
  templateUrl: './order.html',
  styleUrl: './order.scss'
})
export class Order implements OnInit{

  orderServic: OrderService = inject( OrderService );
  orders: OrderWithClientInterface[] = [];

  ngOnInit (){
    this.orderServic.getOrdersWithClients().subscribe({
      next: (orders: OrderWithClientInterface[]) => {
        this.orders = orders
        console.log( this.orders )
      }
    })
  }

  checkBadgeStyling (status: string | null | undefined): string {
    if ( status == "stand by" ) {
      return "bg-red-100 text-red-800"
    }
    if ( status == "tailoring" || status == "stitching" || status == "tailoring" ) {
      return "bg-yellow-100 text-yellow-800"
    }
    if ( status == "final touches" ) {
      return "bg-blue-100 text-blue-800"
    }
    return "bg-green-100 text-green-800"
  }


}
