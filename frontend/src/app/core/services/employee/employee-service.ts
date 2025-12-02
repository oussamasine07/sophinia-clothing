import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {EmployeeInterface} from '../../../models/interfaces/employee-interface';
import {employeeFormtype} from '../../../models/types/employeeFormType';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  httpClient: HttpClient = inject(HttpClient);
  url: string = "http://localhost:8090/api/v1/employee"

  getEmployeesList (): Observable<EmployeeInterface[]> {
    return this.httpClient.get<EmployeeInterface[]>(this.url)
  }

  createEmployee (body: employeeFormtype): Observable<EmployeeInterface> {
    return this.httpClient.post<EmployeeInterface>(this.url, body).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(() => err)
      })
    )
  }

  deleteEmployee (id: number) {
    return this.httpClient.delete(`${this.url}/${id}`);
  }

}
















