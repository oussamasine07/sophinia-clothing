import {Component, inject, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {routes} from '../../../app.routes';

@Component({
  selector: 'app-index',
  imports: [],
  templateUrl: './index.html',
  styleUrl: './index.css'
})
export class Index implements OnInit {

  router: Router = inject(Router);

  ngOnInit() {
    this.router.navigate(["/app"]);
  }


}
