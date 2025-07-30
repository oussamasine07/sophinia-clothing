import {Component, inject} from '@angular/core';
import {Auth} from '../../../../services/auth/auth';
import {FormsModule} from '@angular/forms';
import {loginFromType} from '../../../../models/types/loginFormType';
import {Token} from '../../../../models/interfaces/token';

@Component({
  selector: 'app-admin',
  imports: [
    FormsModule
  ],
  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin {

  authService: Auth = inject( Auth );

  loginObj: loginFromType = {
    email: "",
    password: ""
  }

  onLoginSubmit (form: FormsModule) {
    this.authService.authenticateUser( this.loginObj ).subscribe({
      next: (res: Token) => {
        console.log( res );

        this.loginObj = {
          email: "",
          password: ""
        }
      },
      error: (err) => {
        console.log( err )
      }
    })
  }

}

















