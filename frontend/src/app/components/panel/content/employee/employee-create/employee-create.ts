import {Component, EventEmitter, inject, OnInit, Output} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgClass, NgIf} from '@angular/common';
import {employeeFormtype} from '../../../../../models/types/employeeFormType';
import {EmployeeService} from '../../../../../core/services/employee/employee-service';
import {EmployeeInterface} from '../../../../../models/interfaces/employee-interface';

@Component({
  selector: 'app-employee-create',
  imports: [
    FormsModule,
    NgIf,
    NgClass
  ],
  templateUrl: './employee-create.html',
  styleUrl: './employee-create.scss'
})
export class EmployeeCreate implements OnInit {

  employeeService: EmployeeService = inject(EmployeeService);

  animate: boolean = false;
  ngOnInit (){
    setTimeout(() => {
      this.animate = true
    }, 10)
  }

  fieldErrors: Record<string, string | string[]> = {}

  @Output() close = new EventEmitter();
  onCloseClick () {
    this.animate = false;
    setTimeout(() => {
      this.close.emit();
    }, 300)
  }

  employeeForm: employeeFormtype = {
    firstName: "",
    lastName: "",
    email: "",
    password: ""
  }

  @Output() employee = new EventEmitter();
  onCreateDesignSubmit (form: FormsModule) {
    this.employeeService.createEmployee(this.employeeForm).subscribe({
      next: (e: EmployeeInterface) => {
        this.employee.emit(e);
        this.employeeForm = {
          firstName: "",
          lastName: "",
          email: "",
          password: ""
        }
        this.onCloseClick();
      },
      error: (err) => {
        this.fieldErrors = err.error;
      }
    })
  }

  onGeneratePasswordClick () {
    const chars: string[] = [
      "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
      "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
      "0","1","2","3","4","5","6","7","8","9",
      "!","@","#","$","-","_","/","?","|"
    ]

    const passwordChars = []

    for (let i = 0; i < 7; i++) {
      const index = Math.floor(Math.random() * chars.length - 1)
      passwordChars.push(chars[index])
    }

    const password: string = passwordChars.join("");

    this.employeeForm.password = password
  }

  showPassword = false
  onTogglePassword () {
    this.showPassword = !this.showPassword
  }


}






















