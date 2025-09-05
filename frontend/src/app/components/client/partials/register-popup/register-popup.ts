import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-register-popup',
  imports: [
    NgClass
  ],
  templateUrl: './register-popup.html',
  styleUrl: './register-popup.scss'
})
export class RegisterPopup implements OnInit {

  orderFromLocalStorage: any = (() => {
    const client = localStorage.getItem("client");
    return client ? JSON.parse(client) : null;
  })();

  clientName: string | null = null;

  animate: boolean = false;
  ngOnInit (){
    setTimeout(() => {
      this.animate = true
    }, 10)

    this.clientName = this.orderFromLocalStorage.firstName;
  }

  @Output() close = new EventEmitter();
  @Output() cancelRegister = new EventEmitter();

  onCancelRegisterClick () {
    this.animate = false;
    setTimeout(() => {
      this.close.emit();
    }, 300)
  }

  @Output() confirmRegister = new EventEmitter();
  onConfirmRegisterClick () {
    this.confirmRegister.emit();
  }


}
