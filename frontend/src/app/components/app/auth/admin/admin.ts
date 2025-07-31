import {Component, inject, OnInit} from '@angular/core';
import {Auth} from '../../../../services/auth/auth';
import {FormsModule} from '@angular/forms';
import {loginFromType} from '../../../../models/types/loginFormType';
import {Token} from '../../../../models/interfaces/token';
import {Router} from '@angular/router';

@Component({
  selector: 'app-admin',
  imports: [
    FormsModule
  ],
  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin implements OnInit {

  authService: Auth = inject( Auth );
  router: Router = inject(Router);

  token: string | null = localStorage.getItem('token') ? localStorage.getItem('token') : null;

  ngOnInit () {
    if (this.token) {
      this.router.navigate(['/app'])
    }
  }

  loginObj: loginFromType = {
    email: "",
    password: ""
  }

  onLoginSubmit (form: FormsModule) {
    this.authService.authenticateUser( this.loginObj ).subscribe({
      next: (res: Token) => {

        localStorage.setItem('token', res.token)
        this.token = res.token;

        this.loginObj = {
          email: "",
          password: ""
        }

        this.router.navigate(['/app'])
      },
      error: (err) => {
        console.log( err )
      }
    })
  }

}

















