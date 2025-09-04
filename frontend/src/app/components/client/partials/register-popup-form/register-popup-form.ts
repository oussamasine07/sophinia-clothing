import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgClass, NgIf} from '@angular/common';

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

  clientFromLocalStorage: any = (() => {
    const order = localStorage.getItem("client");
    return order ? JSON.parse(order) : null;
  })();

  animate: boolean = false;
  ngOnInit (){
    setTimeout(() => {
      this.animate = true
    }, 10)

    console.log( this.clientFromLocalStorage )

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

  }



}
