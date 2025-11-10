import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {ClothingModelInterface} from '../../../models/interfaces/clothing-model-interface';

@Injectable({
  providedIn: 'root'
})
export class ClothingModelService {

  httpClient: HttpClient = inject( HttpClient );

  url: string = "http://localhost:8090/api/v1/clothing-model";

  getClothingModels (): Observable<ClothingModelInterface[]> {
    return this.httpClient.get<ClothingModelInterface[]>(this.url);
  }

  getClothingModelById ( id: number ) {
    return this.httpClient.get<ClothingModelInterface>(`${this.url}/${id}`);
  }

  createClothingModel (formData: FormData): Observable<ClothingModelInterface> {
    return this.httpClient.post<ClothingModelInterface>(this.url, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );

  }

  updateClothingModel (formData: FormData, id: number | null | undefined): Observable<ClothingModelInterface> {
    return this.httpClient.put<ClothingModelInterface>(`${this.url}/${id}`, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );
  }

  deleteClothingModel ( id: number | null | undefined ): Observable<any> {
    return this.httpClient.delete<any>(`${this.url}/${id}`);
  }
}
