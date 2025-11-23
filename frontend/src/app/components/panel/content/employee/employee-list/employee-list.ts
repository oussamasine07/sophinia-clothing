import {Component, inject, OnInit} from '@angular/core';
import {EmployeeInterface} from '../../../../../models/interfaces/employee-interface';
import {EmployeeService} from '../../../../../core/services/employee/employee-service';
import {NgForOf, NgIf} from '@angular/common';
import {EmployeeCreate} from '../employee-create/employee-create';

@Component({
  selector: 'app-employee-list',
  imports: [
    NgForOf,
    NgIf,
    EmployeeCreate
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

  showCreateModel = false;
  openCreateModel () {
    this.showCreateModel = true;
  }
  closeCreateModel () {
    this.showCreateModel = false;
  }

  addEmployee (e: EmployeeInterface) {
    this.employees.push(e)
  }



}


























