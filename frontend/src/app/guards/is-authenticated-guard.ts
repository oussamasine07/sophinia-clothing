import {CanActivateFn, Router,} from '@angular/router';
import {inject} from '@angular/core';

export const isAuthenticatedGuard: CanActivateFn = (route, state) => {

  const router: Router = inject(Router)

  if (!localStorage.getItem('token')) router.navigate(['/app/login']);

  return !!localStorage.getItem('token');
};
