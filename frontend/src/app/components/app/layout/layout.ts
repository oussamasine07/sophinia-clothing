import { Component } from '@angular/core';
import {SideNav} from '../partials/side-nav/side-nav';
import {Navbar} from '../partials/navbar/navbar';

@Component({
  selector: 'app-layout',
  imports: [
    SideNav,
    Navbar
  ],
  templateUrl: './layout.html',
  styleUrl: './layout.css'
})
export class Layout {

}
