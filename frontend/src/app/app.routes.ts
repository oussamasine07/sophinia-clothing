import { Routes } from '@angular/router';
import {Index} from './components/pages/index';
import {Layout} from './components/app/layout/layout';

export const routes: Routes = [
  {
    path: "",
    component: Index
  },
  {
    path: "app",
    component: Layout
  }
];
