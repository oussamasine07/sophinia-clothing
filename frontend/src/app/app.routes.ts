import { Routes } from '@angular/router';
import {Index} from './components/pages/index';
import {Layout} from './components/app/layout/layout';
import {Admin} from './components/app/auth/admin/admin';

export const routes: Routes = [
  {
    path: "",
    component: Index
  },
  {
    path: "app",
    component: Layout
  },
  {
    path: "login",
    component: Admin
  }
];
