import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {DesignInterface} from '../../models/interfaces/design-interface';

@Injectable({
  providedIn: 'root'
})
export class DesignService {

  httpClient:HttpClient = inject(HttpClient);
  url: string = "http://localhost:8090/api/v1/design";

  getDesigns (): Observable<DesignInterface[]> {
    return this.httpClient.get<DesignInterface[]>(this.url);
  }

  getDesignById ( id: number ) {
    return this.httpClient.get<DesignInterface>(`${this.url}/${id}`);
  }

  createDesign (formData: FormData): Observable<DesignInterface> {
    return this.httpClient.post<DesignInterface>(this.url, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );

  }

  updateDesign (formData: FormData, id: number | null | undefined): Observable<DesignInterface> {
    return this.httpClient.put<DesignInterface>(`${this.url}/${id}`, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );
  }

  deleteDesign ( id: number | null | undefined ): Observable<any> {
    return this.httpClient.delete<any>(`${this.url}/${id}`);
  }

}



















