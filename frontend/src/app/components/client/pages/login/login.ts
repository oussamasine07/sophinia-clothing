import { Component } from '@angular/core';
import {Header} from "../../partials/header/header";
import {RemoveDashPipe} from "../../../../pipes/remove-dash-pipe";
import {Footer} from '../../partials/footer/footer';

@Component({
  selector: 'app-login',
  imports: [
    Header,
    RemoveDashPipe,
    Footer
  ],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {

}
