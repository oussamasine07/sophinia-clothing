import {Component, inject, Input, OnInit} from '@angular/core';
import {ProductService} from '../../../../../services/product/product-service';
import {ProductInterface} from '../../../../../models/interfaces/product-interface';
import {NgForOf, NgIf} from '@angular/common';
import {ProductCreate} from '../product-create/product-create';
import {Popup} from '../../../partials/popup/popup';
import {ProductUpdate} from '../product-update/product-update';

@Component({
  selector: 'app-product-list',
  imports: [
    NgForOf, NgIf,
    ProductCreate, ProductUpdate, Popup
  ],
  templateUrl: './product-list.html',
  styleUrl: './product-list.scss'
})
export class ProductList implements OnInit {

  productService: ProductService = inject( ProductService );
  products: ProductInterface[] = [];

  ngOnInit () {
    this.productService.getProducts().subscribe({
      next: (products: ProductInterface[]) => {
        this.products = products;
      }
    })
  }

  showCreateModal = false;
  openCreateModal () {
    this.showCreateModal = true;
  }
  closeCreateModal () {
    this.showCreateModal = false
  }

  addNewProduct (f: ProductInterface) {
    this.products.push( f );
  }

  @Input() currentProduct: ProductInterface | null = null
  showUpdateModal = false;
  openUpdateModal (p: ProductInterface) {
    this.showUpdateModal = true;
    this.currentProduct = p;
  }
  closeUpdateModal () {
    this.showUpdateModal = false;
  }
  updateProduct (product: ProductInterface) {
    this.products = this.products.map((p: ProductInterface) => p.id == product.id ? product : p);
  }

  @Input() currentDeleteType: string = "";
  showDeleteModal = false;
  openDeleteModal ( p: ProductInterface ) {
    this.showDeleteModal = true;
    this.currentProduct = p;
    this.currentDeleteType = "product";
  }
  closeDeleteModal () {
    this.showDeleteModal = false;
    this.currentProduct = null;
  }
  deleteModal ( product: ProductInterface ) {
    this.products = this.products.filter( p => p.id != product.id)
    this.closeDeleteModal()
  }

}
