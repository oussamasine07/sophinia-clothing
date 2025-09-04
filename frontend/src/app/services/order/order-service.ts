import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, throwError} from 'rxjs';

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

}
