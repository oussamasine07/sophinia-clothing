import { CanActivateFn } from '@angular/router';
import {Auth} from '../services/auth/auth';
import {inject} from '@angular/core';
import {Location} from '@angular/common';

export const isAdminGuard: CanActivateFn = (route, state) => {
  const authService: Auth = inject(Auth)
  const location: Location = inject(Location);

  const token: string | null = localStorage.getItem('token');
  // get user role from token
  const role: string | null = authService.getUserRole( token );

  if ( role == "admin" ) return true;
  location.back();
  return false;
};
