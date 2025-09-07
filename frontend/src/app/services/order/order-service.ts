import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {OrderWithClientInterface} from '../../models/interfaces/order-with-client-interface';
import {OrderDetailsInterface} from '../../models/interfaces/order-details-interface';

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

  getOrderDetails ( id: number | null | undefined ): Observable<OrderDetailsInterface> {
    return this.httpClient.get<OrderDetailsInterface>(`${this.url}/details/${ id }`);
  }

  setMeasures (body: any): Observable<any> {
    return this.httpClient.post(`${this.url}/set-measures`, body).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );
  }

}
