import {Component, inject, Input, input, OnInit} from '@angular/core';
import {EmployeeInterface} from '../../../../../models/interfaces/employee-interface';
import {EmployeeService} from '../../../../../core/services/employee/employee-service';
import {NgForOf, NgIf} from '@angular/common';
import {EmployeeCreate} from '../employee-create/employee-create';
import {Popup} from '../../../partials/popup/popup';

@Component({
  selector: 'app-employee-list',
  imports: [
    NgForOf,
    NgIf,
    EmployeeCreate,
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

  @Input() currentDeleteType: string = "";
  @Input() currentEmployee: EmployeeInterface | null = null;
  showDeleteModal: boolean = false;
  openPopupModal (e: EmployeeInterface) {
    this.showDeleteModal = true;
    this.currentEmployee = e;
    this.currentDeleteType = "employee"
  }

  closeDeleteModal () {
    this.showDeleteModal = false;
    this.currentEmployee = null;
    this.currentDeleteType = "";
  }

  deleteModal (e: EmployeeInterface) {
    this.employees = this.employees.filter(employee => employee.id != e.id);
    this.closeDeleteModal()
  }




}


























