import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token: string | null = localStorage.getItem("token");

  const isAuthEndpoint = req.url.includes('/app/login/admin');

  if (!isAuthEndpoint) {
    return next(req);
  }

  // const url = new URL(req.url, window.location.origin);
  // const isPublicEventsGet = req.method === 'GET' && url.pathname.startsWith('/app');
  // if (isPublicEventsGet) {
  //   return next(req);
  // }

  const newReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`
    }
  });

  return next(newReq);
};
