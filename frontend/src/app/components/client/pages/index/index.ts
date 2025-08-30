import { Component } from '@angular/core';
import {Header} from '../../partials/header/header';
import {Footer} from '../../partials/footer/footer';

@Component({
  selector: 'app-index',
  imports: [
    Header, Footer
  ],
  templateUrl: './index.html',
  styleUrl: './index.scss',
  standalone: true
})
export class Index {

}
