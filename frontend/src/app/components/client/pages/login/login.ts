import {Component, inject} from '@angular/core';
import {Header} from "../../partials/header/header";
import {Footer} from '../../partials/footer/footer';
import {Auth} from '../../../../core/services/auth/auth';
import {Router} from '@angular/router';
import {loginFromType} from '../../../../models/types/loginFormType';
import {FormsModule} from '@angular/forms';
import {Token} from '../../../../models/interfaces/token';

@Component({
  selector: 'app-login',
  imports: [
    Header,
    Footer,
    FormsModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {
  authService: Auth = inject( Auth );
  router: Router = inject(Router);

  token: string | null = localStorage.getItem('token') ? localStorage.getItem('token') : null;

  loginObj: loginFromType = {
    email: "",
    password: ""
  }

  onLoginSubmit (form: FormsModule) {
    this.authService.authenticateUser( this.loginObj ).subscribe({
      next: (res: Token) => {

        localStorage.setItem('token', res.token)
        this.token = res.token;

        console.log(res)

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
