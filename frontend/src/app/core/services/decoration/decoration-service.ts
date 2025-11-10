import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {DecorationInterface} from '../../../models/interfaces/decoration-interface';

@Injectable({
  providedIn: 'root'
})
export class DecorationService {

  httpClient: HttpClient = inject( HttpClient );

  url: string = "http://localhost:8090/api/v1/decoration";

  getDecorations (): Observable<DecorationInterface[]> {
    return this.httpClient.get<DecorationInterface[]>(this.url);
  }

  getDecorationById ( id: number ) {
    return this.httpClient.get<DecorationInterface>(`${this.url}/${id}`);
  }

  createDecoration (formData: FormData): Observable<DecorationInterface> {
    return this.httpClient.post<DecorationInterface>(this.url, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );

  }

  updateDecoration (formData: FormData, id: number | null | undefined): Observable<DecorationInterface> {
    return this.httpClient.put<DecorationInterface>(`${this.url}/${id}`, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );
  }

  deleteDecoration ( id: number | null | undefined ): Observable<any> {
    return this.httpClient.delete<any>(`${this.url}/${id}`);
  }
}
