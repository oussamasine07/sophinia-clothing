import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {ClothingTypeInterface} from '../../../models/interfaces/clothing-type-interface';

@Injectable({
  providedIn: 'root'
})
export class ClothingTypeService {

  httpClient: HttpClient = inject( HttpClient );

  url: string = "http://localhost:8090/api/v1/clothing-type";

  getClothingTypes (): Observable<ClothingTypeInterface[]> {
    return this.httpClient.get<ClothingTypeInterface[]>(this.url);
  }

  getClothingTypeById ( id: number ) {
    return this.httpClient.get<ClothingTypeInterface>(`${this.url}/${id}`);
  }

  createClothingType (formData: FormData): Observable<ClothingTypeInterface> {
    return this.httpClient.post<ClothingTypeInterface>(this.url, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );

  }

  updateClothingType (formData: FormData, id: number | null | undefined): Observable<ClothingTypeInterface> {
    return this.httpClient.put<ClothingTypeInterface>(`${this.url}/${id}`, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );
  }

  deleteClothingType ( id: number | null | undefined ): Observable<any> {
    return this.httpClient.delete<any>(`${this.url}/${id}`);
  }

}
