import {Component, inject, OnInit} from '@angular/core';
import {RouterLink} from '@angular/router';
import {DesignService} from '../../../../../services/design/design-service';
import {DesignInterface} from '../../../../../models/interfaces/design-interface';
import {NgFor} from '@angular/common';

@Component({
  selector: 'app-design-list',
  imports: [
    RouterLink,
    NgFor
  ],
  templateUrl: './design-list.html',
  styleUrl: './design-list.css'
})
export class DesignList implements OnInit{

  designs: DesignInterface[] = []

  designService: DesignService = inject( DesignService );

  ngOnInit () {
    this.designService.getDesigns().subscribe({
      next: (d: DesignInterface[]) => {
        this.designs = d;
      }
    })
  }


}
