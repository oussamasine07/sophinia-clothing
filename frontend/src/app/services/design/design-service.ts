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

  createDesign (formData: FormData): Observable<DesignInterface> {
    return this.httpClient.post<DesignInterface>(this.url, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );

  }

}
