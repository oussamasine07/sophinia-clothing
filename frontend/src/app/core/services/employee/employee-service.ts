import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {EmployeeInterface} from '../../../models/interfaces/employee-interface';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  httpClient: HttpClient = inject(HttpClient);
  url: string = "http://localhost:8090/api/v1/employee"

  getEmployeesList (): Observable<EmployeeInterface[]> {
    return this.httpClient.get<EmployeeInterface[]>(this.url)
  }

}
