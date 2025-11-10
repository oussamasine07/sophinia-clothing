import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {FabricInterface} from '../../../models/interfaces/fabric-interface';

@Injectable({
  providedIn: 'root'
})
export class FabricService {

  httpClient: HttpClient = inject( HttpClient );

  url: string = "http://localhost:8090/api/v1/fabric";

  getFabrics (): Observable<FabricInterface[]> {
    return this.httpClient.get<FabricInterface[]>(this.url);
  }

  getFabricById ( id: number ): Observable<FabricInterface> {
    return this.httpClient.get<FabricInterface>(`${this.url}/${id}`);
  }

  createFabric (formData: FormData): Observable<FabricInterface> {
    return this.httpClient.post<FabricInterface>(this.url, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );

  }

  // take a look here in the update
  updateFabric (formData: FormData, id: number | null | undefined): Observable<FabricInterface> {
    return this.httpClient.put<FabricInterface>(`${this.url}/${id}`, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );
  }

  deleteFabric ( id: number | null | undefined ): Observable<any> {
    return this.httpClient.delete<any>(`${this.url}/${id}`);
  }

}
