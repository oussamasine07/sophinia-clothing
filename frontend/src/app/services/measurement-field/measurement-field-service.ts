import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ProductInterface} from '../../models/interfaces/product-interface';

@Injectable({
  providedIn: 'root'
})
export class MeasurementFieldService {

  httpClient: HttpClient = inject( HttpClient );

  url: string = "http://localhost:8090/api/v1/measurement-field";

  getMeasurementFields (): Observable<ProductInterface[]> {
    return this.httpClient.get<ProductInterface[]>(this.url);
  }

}
