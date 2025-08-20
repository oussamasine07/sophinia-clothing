import { Routes } from '@angular/router';
import {Index} from './components/pages/index';
import {Layout} from './components/app/layout/layout';
import {Admin} from './components/app/auth/admin/admin';
import {DesignCreate} from './components/app/content/design/design-create/design-create';
import {DesignList} from './components/app/content/design/design-list/design-list';
import {DesignUpdate} from './components/app/content/design/design-update/design-update';
import {DecorationList} from './components/app/content/decoration/decoration-list/decoration-list';

export const routes: Routes = [
  {
    path: "",
    component: Index
  },
  {
    path: "app",
    component: Layout,
    children: [
      {
        path: "design",
        component: DesignList
      },
      {
        path: "decoration",
        component: DecorationList
      }
    ]
  },
  {
    path: "login",
    component: Admin
  }
];
