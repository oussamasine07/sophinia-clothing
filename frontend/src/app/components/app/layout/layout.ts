import {Component, inject, OnInit} from '@angular/core';
import {SideNav} from '../partials/side-nav/side-nav';
import {Navbar} from '../partials/navbar/navbar';
import {Router, RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-layout',
  imports: [
    SideNav,
    Navbar,
    RouterOutlet
  ],
  templateUrl: './layout.html',
  styleUrl: './layout.css'
})
export class Layout implements OnInit {

  router: Router = inject(Router);
  token: string | null = localStorage.getItem('token');

  ngOnInit () {
    if (!this.token) {
      this.router.navigate(["/login"])
    }
  }

}
