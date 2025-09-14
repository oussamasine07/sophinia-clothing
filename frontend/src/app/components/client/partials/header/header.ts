import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-header',
  imports: [
    RouterLink,
    NgClass
  ],
  templateUrl: './header.html',
  styleUrl: './header.scss',
  standalone: true
})
export class Header {

  showMobileNav: boolean = false;

  onShowMobileNavClick () {
    this.showMobileNav = !this.showMobileNav;
  }

}
