import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Token} from '../../models/interfaces/token';
import {loginFromType} from '../../models/types/loginFormType';

@Injectable({
  providedIn: 'root'
})
export class Auth {

  httpClient: HttpClient = inject( HttpClient );

  authenticateUser (body: loginFromType): Observable<Token> {
    return this.httpClient.post<Token>("http://localhost:8090/app/admin/login", body).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );
  }

}
