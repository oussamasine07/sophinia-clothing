import {measurementFieldFormType} from './measurementFieldformType';

export type  productFromType = {
  name: string,
  description: string,
  clothing_type: number | null,
  measurements_fields_ids: number[] | null,
  measurement_fields: measurementFieldFormType[] | null
}
