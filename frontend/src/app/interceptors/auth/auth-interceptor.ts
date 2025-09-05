import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token: string | null = localStorage.getItem("token");

  const getEndPoints: string[] = [
    '/api/v1/product',
    '/api/v1/clothing-type',
    '/api/v1/clothing-model',
    '/api/v1/decoration',
    '/api/v1/design',
    '/api/v1/fabric'
  ]

  const postEndPoints: string[] = [
    '/app/login',
    '/api/v1/order/place-order',
  ]

  const putEndPoints: string[] = [
    '/api/v1/client/update-client-after-order'
  ]

  const isGetOnly: boolean = getEndPoints.some(url => req.url.includes( url )) && req.method === 'GET'
  const isPostAllowed: boolean = postEndPoints.some( url => req.url.includes(url)) && req.method === 'POST'
  const isPutAllowed: boolean = putEndPoints.some( url => req.url.includes(url)) && req.method === 'PUT'

  // const isAuthEndpoint = req.url.includes('/app/login') || req.url.includes('/api/v1/product');

  const isAllowedEndPoint: boolean = isGetOnly || isPostAllowed || isPutAllowed;
  if (isAllowedEndPoint) {
    console.log("ignore interceptore")
    return next(req);
  }


  const newReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`
    }
  });

  return next(newReq);
};
