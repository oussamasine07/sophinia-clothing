import {AfterViewInit, Component, ElementRef, ViewChild} from '@angular/core';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-side-nav',
  imports: [
    RouterLink
  ],
  templateUrl: './side-nav.html',
  styleUrl: './side-nav.scss',
  standalone: true
})
export class SideNav implements AfterViewInit {

  @ViewChild("asideComp") asideCompRef!: ElementRef<HTMLDivElement>;
  @ViewChild("logoContainer") logoContainerRef!: ElementRef<HTMLDivElement>;
  @ViewChild("logo") logoRef!: ElementRef<HTMLImageElement>;
  @ViewChild("nav") navRef!: ElementRef<HTMLDivElement>;



  navHeight: any = 0;

  ngAfterViewInit () {

    const logo = this.logoRef.nativeElement;
    const aside = this.asideCompRef.nativeElement;
    const logoContainer = this.logoContainerRef.nativeElement

    const calculateRest = () => {
      const asideHeight = aside.offsetHeight;
      const logoHeight = logo.offsetHeight;
      const logoStyles = window.getComputedStyle( logoContainer );
      const logoMarginBottom = parseInt(logoStyles.marginBottom)
      this.navHeight = asideHeight - logoHeight - logoMarginBottom - 60;

      console.log('asideHeight', asideHeight);
      console.log('logoHeight', logoHeight);
      console.log('the rest is ', this.navHeight)
    };

    if (logo.complete) {
      // image already loaded
      calculateRest();
    } else {
      // wait for image to load
      logo.onload = () => calculateRest();
    }
  }

}
