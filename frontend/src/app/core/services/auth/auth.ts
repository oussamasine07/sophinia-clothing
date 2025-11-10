import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Token} from '../../../models/interfaces/token';
import {loginFromType} from '../../../models/types/loginFormType';
import {User} from '../../../models/interfaces/user';
import {jwtDecode} from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class Auth {

  httpClient: HttpClient = inject( HttpClient );

  authenticateUser (body: loginFromType): Observable<Token> {
    return this.httpClient.post<Token>("http://localhost:8090/app/login", body).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );
  }

  // get decoded token from the localstorage
  getDecodedToken ( token: string | null ): User | null {
    return token ? jwtDecode<User>( token ) : null;
  }

  // check if token is expired
  isTokenExpired ( token: string | null ): boolean {
    if (token) {
      const decoded: any = jwtDecode(token)
      const decodedExpDate = decoded.exp;
      const currentDate = Date.now() / 1000;

      return decodedExpDate < currentDate;
    }
    return true;
  }

  // get user role from token
  getUserRole ( token: string | null ) {
    const decoded: User | null = this.getDecodedToken(token);
    return decoded ? decoded.role : "";
  }

}
