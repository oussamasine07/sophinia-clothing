import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-navbar',
  imports: [],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
  standalone: true
})
export class Navbar {
  @Output() toggleSidebar = new EventEmitter<void>();
}
