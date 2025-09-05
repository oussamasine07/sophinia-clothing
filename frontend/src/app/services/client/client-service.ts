import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {FabricInterface} from '../../models/interfaces/fabric-interface';
import {catchError, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  httpClient: HttpClient = inject( HttpClient );

  url: string = "http://localhost:8090/api/v1/client";

  updateClientAfterOrder (body: any) {
    console.log( body )
    return this.httpClient.put(`${this.url}/update-client-after-order`, body).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );
  }

}
