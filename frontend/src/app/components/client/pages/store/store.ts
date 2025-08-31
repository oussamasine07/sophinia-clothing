import { Component } from '@angular/core';
import {Header} from '../../partials/header/header';
import {Footer} from '../../partials/footer/footer';

@Component({
  selector: 'app-store',
  imports: [
    Header, Footer
  ],
  templateUrl: './store.html',
  styleUrl: './store.scss'
})
export class Store {

}
