import {Component, inject, OnInit} from '@angular/core';
import {Header} from '../../partials/header/header';
import {Footer} from '../../partials/footer/footer';
import {ProductService} from '../../../../services/product/product-service';
import {ClothingModelService} from '../../../../services/clothing-model/clothing-model-service';
import {ClothingTypeService} from '../../../../services/clothing-type/clothing-type-service';
import {DecorationService} from '../../../../services/decoration/decoration-service';
import {DesignService} from '../../../../services/design/design-service';
import {FabricService} from '../../../../services/fabric/fabric-service';
import {orderTypeForm} from '../../../../models/types/orderTypeForm';
import {storeStepsType} from '../../../../models/types/storeStepsType';
import {ProductInterface} from '../../../../models/interfaces/product-interface';
import {ClothingTypeInterface} from '../../../../models/interfaces/clothing-type-interface';
import {ClothingModelInterface} from '../../../../models/interfaces/clothing-model-interface';
import {DecorationInterface} from '../../../../models/interfaces/decoration-interface';
import {DesignInterface} from '../../../../models/interfaces/design-interface';
import {FabricInterface} from '../../../../models/interfaces/fabric-interface';
import {NgClass, NgIf} from '@angular/common';
import {ProductCard} from '../../partials/product-card/product-card';
import {clientTypeForm} from '../../../../models/types/clientTypeForm';
import {availabilityFormType} from '../../../../models/types/availabilityFormType';
import {FormsModule} from '@angular/forms';
import {OrderService} from '../../../../services/order/order-service';
import {RegisterPopup} from '../../partials/register-popup/register-popup';
import {RegisterPopupForm} from '../../partials/register-popup-form/register-popup-form';
import {RemoveDashPipe} from '../../../../pipes/remove-dash-pipe';

type OrderKey =
  "product" | "clothingModel" | "clothingType" | "decoration" | "design" | "fabric" | "current";


@Component({
  selector: 'app-store',
  imports: [
    FormsModule, NgIf, RemoveDashPipe, NgClass,
    Header, Footer, ProductCard, RegisterPopup,
    RegisterPopupForm
  ],
  templateUrl: './store.html',
  styleUrl: './store.scss'
})
export class Store implements OnInit {

  orderService: OrderService = inject(OrderService);
  productService: ProductService = inject(ProductService);
  clothingModelService: ClothingModelService = inject(ClothingModelService);
  clothingTypeService: ClothingTypeService = inject( ClothingTypeService );
  decorationService: DecorationService = inject( DecorationService );
  designService: DesignService = inject( DesignService );
  fabricService: FabricService = inject( FabricService );

  stepIdx: number = 0;
  steps: string[] = ["clothing-type", "clothing-model", "product", "fabric", "decoration", "design", "last-step"]
  currentLable: string = this.steps[ this.stepIdx ];

  orderFromLocalStorage: any = (() => {
    const order = localStorage.getItem("order");
    return order ? JSON.parse(order) : null;
  })();

  order: {
    product: ProductInterface | null,
    clothingModel: ClothingModelInterface | null,
    clothingType: ClothingTypeInterface | null,
    decoration: DecorationInterface | null,
    design: DesignInterface | null,
    fabric: FabricInterface | null,
    current: number
  } = {
    product: null,
    clothingModel: null,
    clothingType: null,
    decoration: null,
    design: null,
    fabric: null,
    current: 0
  }

  cards:
    ProductInterface[]
    | ClothingTypeInterface[]
    | ClothingModelInterface[]
    | DecorationInterface[]
    | DesignInterface[]
    | FabricInterface[]
    | null = null;

  client: clientTypeForm = {
    lastName: null,
    firstName: null,
    email: null,
    phone: null,
    address: null,
    city: null,
    postalCode: null
  }

  availability: availabilityFormType = {
    startDate: null,
    endDate: null,
    availabilityType: null
  }

  ngOnInit () {

    this.order = {
      product: this.orderFromLocalStorage ? this.orderFromLocalStorage.product : null,
      clothingModel: this.orderFromLocalStorage ? this.orderFromLocalStorage.clothingModel : null,
      clothingType: this.orderFromLocalStorage ? this.orderFromLocalStorage.clothingType : null,
      decoration: this.orderFromLocalStorage ? this.orderFromLocalStorage.decoration : null,
      design: this.orderFromLocalStorage ? this.orderFromLocalStorage.design : null,
      fabric: this.orderFromLocalStorage ? this.orderFromLocalStorage.fabric : null,
      current: this.orderFromLocalStorage ? this.orderFromLocalStorage.current : 0
    }

    this.stepIdx = this.orderFromLocalStorage ? this.orderFromLocalStorage.current : 0;
    this.currentLable = this.steps[ this.stepIdx ];
    this.initializeCards( this.currentLable )

  }

  initializeCards (current: string) {
    switch ( current ) {
      case "product":
        this.productService.getProducts().subscribe({
          next: ( products: ProductInterface[] ) => {
            this.cards = products
            console.log( this.cards );
          }
        })
        break;
      case "clothing-model":
        this.clothingModelService.getClothingModels().subscribe({
          next: ( clothingModels: ClothingModelInterface[] ) => {
            this.cards = clothingModels
            console.log( this.cards );
          }
        })
        break;
      case "clothing-type":
        this.clothingTypeService.getClothingTypes().subscribe({
          next: ( clothingTypes: ClothingTypeInterface[] ) => {
            this.cards = clothingTypes
            console.log(this.cards)
          }
        })
        break;
      case "decoration":
        this.decorationService.getDecorations().subscribe({
          next: ( decorations: DecorationInterface[] ) => {
            this.cards = decorations
            console.log( this.cards );
          }
        })
        break;
      case "design":
        this.designService.getDesigns().subscribe({
          next: ( designs: DesignInterface[] ) => {
            this.cards = designs
            console.log( this.cards );
          }
        })
        break;
      case "fabric":
        this.fabricService.getFabrics().subscribe({
          next: ( fabrics: FabricInterface[] ) => {
            this.cards = fabrics
            console.log( this.cards );
          }
        })
        break;
    }
  }

  changeCurrentStep ( c: any ) {

    const { choice, currentChoice } = c;

    this.order = {
      product: currentChoice == "product" ? choice : this.order.product,
      clothingModel: currentChoice == "clothing-model" ? choice : this.order.clothingModel,
      clothingType: currentChoice == "clothing-type" ? choice : this.order.clothingType,
      decoration: currentChoice == "decoration" ? choice : this.order.decoration,
      design: currentChoice == "design" ? choice : this.order.design,
      fabric: currentChoice == "fabric" ? choice : this.order.fabric,
      current: this.stepIdx + 1
    }

    localStorage.setItem("order", JSON.stringify(this.order))

    this.stepIdx = this.stepIdx >= this.steps.length - 1 ? 0 : this.stepIdx + 1;
    this.currentLable = this.steps[ this.stepIdx ]
    this.initializeCards( this.currentLable )

  }

  fieldErrors: Record<string, string | string[]> = {}
  onOrderSubmit (form: FormsModule) {

    const orderBody = {
      productId: this.order.product?.id,
      clothingModelId: this.order.clothingModel?.id,
      decorationId: this.order.decoration?.id,
      designId: this.order.design?.id,
      fabricId: this.order.fabric?.id,
      client: {
        firstName: this.client.firstName,
        lastName: this.client.lastName,
        email: this.client.email,
        phone: this.client.phone
      },
      availability : {
        startDate: this.availability.startDate,
        endDate: this.availability.endDate,
        availabilityType: this.availability.availabilityType
      }
    }

    console.log( orderBody )

    // register client details in the localstorage
    localStorage.setItem('client', JSON.stringify(orderBody.client))

    this.orderService.placeOrder( orderBody ).subscribe({
      next: (res) => {
        this.client = {
          lastName: "",
          firstName: "",
          email: "",
          phone: "",
          address: "",
          city: "",
          postalCode: ""
        }

        this.availability = {
          startDate: "",
          endDate: "",
          availabilityType: ""
        }

        // show register popup
        this.openRegisterPopup();

        this.stepIdx = 0;
        this.currentLable = this.steps[ this.stepIdx ]
        this.initializeCards( this.currentLable )
        localStorage.removeItem("order")
      },
      error: (err) => {
        this.fieldErrors = err.error;
        console.log( this.fieldErrors["availability.availabilityType"] )
      }
    })
  }

  showRegisterPopup = true;
  openRegisterPopup () {
    this.showRegisterPopup = true;
  }

  closeRegisterPopup () {
    this.showRegisterPopup = false
    if (localStorage.getItem("client")) {
      localStorage.removeItem("client");
    }
  }

  showRegisterFormModel = false
  showRegisterForm () {
    this.showRegisterPopup = false
    setTimeout(() => {
      this.showRegisterFormModel = true;
    }, 300)
  }

  closeRegisterForm() {
    this.showRegisterFormModel = false
  }

  toCamelCase(str: string): keyof typeof this.order {
    const camel = str
      .toLowerCase()
      .split(/[-_\s]+/)
      .map((word, index) =>
        index === 0 ? word : word.charAt(0).toUpperCase() + word.slice(1)
      )
      .join("");

    return camel as keyof typeof this.order;
  }

  onStepClick (idx: number, e: Event) {
    e.preventDefault();
    this.stepIdx = idx;
    this.currentLable = this.steps[this.stepIdx]
    this.initializeCards( this.currentLable );
  }
}













