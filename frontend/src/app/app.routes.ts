import { Routes } from '@angular/router';
import {Layout} from './components/app/layout/layout';
import {Admin} from './components/app/auth/admin/admin';
import {DesignList} from './components/app/content/design/design-list/design-list';
import {DecorationList} from './components/app/content/decoration/decoration-list/decoration-list';
import {ClothingTypeList} from './components/app/content/clothing-type/clothing-type-list/clothing-type-list';
import {ClothingModelList} from './components/app/content/clothing-model/clothing-model-list/clothing-model-list';
import {FabricList} from './components/app/content/fabric/fabric-list/fabric-list';
import {ProductList} from './components/app/content/product/product-list/product-list';
import {Index} from './components/client/pages/index';
import {Store} from './components/client/pages/store/store';

export const routes: Routes = [
  {
    path: "",
    component: Index
  },
  {
    path: "store",
    component: Store
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
      },
      {
        path: "clothing-type",
        component: ClothingTypeList
      },
      {
        path: "clothing-model",
        component: ClothingModelList
      },
      {
        path: "fabric",
        component: FabricList
      },
      {
        path: "product",
        component: ProductList
      }
    ]
  },
  {
    path: "login",
    component: Admin
  }
];
