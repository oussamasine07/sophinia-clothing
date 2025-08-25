import {measurementFieldFormType} from './measurementFieldformType';
import {MeasurementFieldInterface} from '../interfaces/measurement-field-interface';

export type  productFromType = {
  name: string,
  description: string,
  clothing_type: number | null,
  measurements_fields_ids: number[] | null,
  measurement_fields: measurementFieldFormType[] | null
}
