import {ProductInterface} from '../interfaces/product-interface';
import {ClothingModelInterface} from '../interfaces/clothing-model-interface';
import {ClothingTypeInterface} from '../interfaces/clothing-type-interface';
import {DecorationInterface} from '../interfaces/decoration-interface';
import {DesignInterface} from '../interfaces/design-interface';
import {FabricInterface} from '../interfaces/fabric-interface';

export type storeStepsType = {
  products : ProductInterface[] | null,
  clothingModels: ClothingModelInterface[] | null,
  clothingTypes: ClothingTypeInterface[] | null,
  decorations: DecorationInterface[] | null,
  designs: DesignInterface[] | null,
  fabrics: FabricInterface[] | null,
  // currentStep: string
}
