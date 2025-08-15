import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {DesignInterface} from '../../models/interfaces/design-interface';

@Injectable({
  providedIn: 'root'
})
export class DesignService {

  httpClient:HttpClient = inject(HttpClient);

  createDesign (formData: FormData): Observable<DesignInterface> {

    const url = "http://localhost:8090/api/v1/design";

    return this.httpClient.post<DesignInterface>(url, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );

  }

}
