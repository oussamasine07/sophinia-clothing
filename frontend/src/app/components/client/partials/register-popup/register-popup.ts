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
  animate: boolean = false;
  ngOnInit (){
    setTimeout(() => {
      this.animate = true
    }, 10)
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
