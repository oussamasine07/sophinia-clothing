import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {ProductInterface} from '../../../models/interfaces/product-interface';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  httpClient: HttpClient = inject( HttpClient );

  url: string = "http://localhost:8090/api/v1/product";

  getProducts (): Observable<ProductInterface[]> {
    return this.httpClient.get<ProductInterface[]>(this.url);
  }

  getProductById ( id: number ): Observable<ProductInterface> {
    return this.httpClient.get<ProductInterface>(`${this.url}/${id}`);
  }

  createProduct (formData: FormData): Observable<ProductInterface> {
    return this.httpClient.post<ProductInterface>(this.url, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );

  }

  // take a look here in the update
  updateProduct (formData: FormData, id: number | null | undefined): Observable<ProductInterface> {
    return this.httpClient.put<ProductInterface>(`${this.url}/${id}`, formData).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err);
      })
    );
  }

  deleteProduct ( id: number | null | undefined ): Observable<any> {
    return this.httpClient.delete<any>(`${this.url}/${id}`);
  }
}
