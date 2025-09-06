import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {OrderWithClientInterface} from '../../models/interfaces/order-with-client-interface';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  httpClient: HttpClient = inject( HttpClient );
  url: string = "http://localhost:8090/api/v1/order";

  placeOrder (formData:any) {
    return this.httpClient.post(`${this.url}/place-order`, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );
  }

  getOrdersWithClients (): Observable<OrderWithClientInterface[]> {
    return this.httpClient.get<OrderWithClientInterface[]>(`${this.url}/get-orders-with-clients`);
  }

}
