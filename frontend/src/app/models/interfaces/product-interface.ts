import {ClothingTypeInterface} from './clothing-type-interface';
import {MeasurementFieldInterface} from './measurement-field-interface';

export interface ProductInterface {
  id: number | null,
  name: string | null,
  description: string | null,
  image: string | null,
  clothingType: ClothingTypeInterface | null,
  productMeasurementFields: MeasurementFieldInterface[] | null
}
