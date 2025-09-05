import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ProductInterface} from '../../../../models/interfaces/product-interface';
import {ClothingTypeInterface} from '../../../../models/interfaces/clothing-type-interface';
import {ClothingModelInterface} from '../../../../models/interfaces/clothing-model-interface';
import {DecorationInterface} from '../../../../models/interfaces/decoration-interface';
import {DesignInterface} from '../../../../models/interfaces/design-interface';
import {FabricInterface} from '../../../../models/interfaces/fabric-interface';

@Component({
  selector: 'app-product-card',
  imports: [],
  templateUrl: './product-card.html',
  styleUrl: './product-card.scss'
})
export class ProductCard {

  @Input() card : ProductInterface
    | ClothingTypeInterface
    | ClothingModelInterface
    | DecorationInterface
    | DesignInterface
    | FabricInterface
    | null  = null;

  @Input() currentChoice: string | null = null

  @Output() choice = new EventEmitter();

  chooseStep( step: any ) {
    const c = {
      choice: step,
      currentChoice: this.currentChoice
    }
    this.choice.emit( c );
  }



}
