import {Component, inject, OnInit} from '@angular/core';
import {EmployeeInterface} from '../../../../../models/interfaces/employee-interface';
import {EmployeeService} from '../../../../../core/services/employee/employee-service';
import {DesignCreate} from '../../design/design-create/design-create';
import {DesignUpdate} from '../../design/design-update/design-update';
import {NgForOf, NgIf} from '@angular/common';
import {Popup} from '../../../partials/popup/popup';

@Component({
  selector: 'app-employee-list',
  imports: [
    NgForOf,
    NgIf,
    Popup
  ],
  templateUrl: './employee-list.html',
  styleUrl: './employee-list.scss'
})
export class EmployeeList implements OnInit {

  employees: EmployeeInterface[] = [];

  employeeService: EmployeeService = inject(EmployeeService);

  ngOnInit () {
    this.employeeService.getEmployeesList().subscribe({
      next: (employees: EmployeeInterface[]) => {
        this.employees = employees;
      },
      error: ( err ) => {
        console.log( err )
      }
    })
  }


}
