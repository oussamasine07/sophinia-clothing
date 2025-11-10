import {Component, EventEmitter, inject, Output} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
  standalone: true
})
export class Navbar {
  @Output() toggleSidebar = new EventEmitter<void>();

  router: Router = inject(Router)

  onLogoutClick() {
    localStorage.removeItem("token");
    this.router.navigate(['/app/login']);
  }
}
