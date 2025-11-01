import { Routes } from '@angular/router';
import {Layout} from './components/panel/layout/layout';
import {Admin} from './components/panel/auth/admin/admin';
import {DesignList} from './components/panel/content/design/design-list/design-list';
import {DecorationList} from './components/panel/content/decoration/decoration-list/decoration-list';
import {ClothingTypeList} from './components/panel/content/clothing-type/clothing-type-list/clothing-type-list';
import {ClothingModelList} from './components/panel/content/clothing-model/clothing-model-list/clothing-model-list';
import {FabricList} from './components/panel/content/fabric/fabric-list/fabric-list';
import {ProductList} from './components/panel/content/product/product-list/product-list';
import {Index} from './components/client/pages/index';
import {Store} from './components/client/pages/store/store';
import {Order} from './components/panel/content/order/order';
import {OrderDetails} from './components/panel/content/order-details/order-details';
import {Login} from './components/client/pages/login/login';

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
    path: "login",
    component: Login
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
      },
      {
        path: "order",
        component: Order
      },
      {
        path: "order/order-details/:id",
        component: OrderDetails
      }
    ]
  },
  {
    path: "app/login",
    component: Admin
  }
];
