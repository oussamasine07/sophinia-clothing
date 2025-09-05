import {Component, EventEmitter, inject, OnInit, Output} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgClass, NgIf} from '@angular/common';
import {ClientService} from '../../../../services/client/client-service';

@Component({
  selector: 'app-register-popup-form',
  imports: [
    FormsModule,
    NgIf, NgClass
  ],
  templateUrl: './register-popup-form.html',
  styleUrl: './register-popup-form.scss'
})
export class RegisterPopupForm implements OnInit {

  clientService: ClientService = inject(ClientService);

  clientFromLocalStorage: any = (() => {
    const order = localStorage.getItem("client");
    return order ? JSON.parse(order) : null;
  })();

  animate: boolean = false;
  ngOnInit (){
    setTimeout(() => {
      this.animate = true
    }, 10)

    this.registerClientFormObj = {
      firstName: this.clientFromLocalStorage.firstName,
      lastName: this.clientFromLocalStorage.lastName,
      email: this.clientFromLocalStorage.email,
      password: "",
      confirmPassword: ""
    }

  }

  fieldErrors: Record<string, string | string[]> = {}

  @Output() close = new EventEmitter();
  onCloseClick () {
    this.animate = false;
    setTimeout(() => {
      this.close.emit();
    }, 300)
  }

  registerClientFormObj = {
    lastName: "",
    firstName: "",
    email: "",
    password: "",
    confirmPassword: ""
  }

  onRegisterClientSubmit (form: FormsModule) {

    this.clientService.updateClientAfterOrder(this.registerClientFormObj).subscribe({
      next: (res) => {
        console.log(res);
        localStorage.removeItem("client");
        this.onCloseClick();
      },
      error: (err) => {
        console.log(err)
        this.fieldErrors = err.error;
      }
    })
  }



}
