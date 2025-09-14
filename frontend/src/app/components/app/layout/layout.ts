import {Component, inject, OnInit} from '@angular/core';
import {SideNav} from '../partials/side-nav/side-nav';
import {Navbar} from '../partials/navbar/navbar';
import {Router, RouterOutlet} from '@angular/router';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-layout',
  imports: [
    SideNav,
    Navbar,
    RouterOutlet,
    NgClass,
  ],
  templateUrl: './layout.html',
  styleUrl: './layout.scss',
  standalone: true
})
export class Layout implements OnInit {

  // router: Router = inject(Router);
  // token: string | null = localStorage.getItem('token');
  //
  // ngOnInit () {
  //   if (!this.token) {
  //     this.router.navigate(["/app/login"])
  //   }
  // }

  router: Router = inject(Router);
  token: string | null = localStorage.getItem('token');

  // Sidebar state
  isSidebarOpen = false;

  ngOnInit() {
    if (!this.token) {
      this.router.navigate(['/app/login']);
    }
  }

  toggleSidebar() {
    this.isSidebarOpen = !this.isSidebarOpen;
  }


}
