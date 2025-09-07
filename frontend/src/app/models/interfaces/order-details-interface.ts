
export interface OrderDetailsInterface {

  orderId: number | null,
  status: string | null,
  product: {
    description: string | null,
    image: string | null,
    fields: {
      measurementId: number | null,
      name: string | null
    }[] | null,
  } | null,
  clothingModel: {
    name: string | null,
    image: string | null
  } | null ,
  decoration: {
    name: string | null,
    image: string | null
  } | null,
  design: {
    name: string | null,
    image: string | null
  } | null,
  client: {
    firstName: string | null,
    lastName: string | null,
    email: string | null,
    phone: string | null,
    address: string | null,
    city: string | null,
    postalCode: string | null
  } | null

}










