import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root',
})
export class ErrorhandlesService {
  constructor(private cookieService: CookieService, private router: Router) {}

  apiError(err: HttpErrorResponse) {
    if (err.status === 403) {
      this.cookieService.delete('token', '/');
      Swal.fire({
        background: '#86198f',
        color: '#fff',
        position: 'center',
        icon: 'info',
        title: 'La sesión caduco. Redireccionando al login.',
        showConfirmButton: false,
        timer: 1500,
      });
      setTimeout(() => {
        this.router.navigate(['/auth/login']);
      }, 1500);
    }
    if (err.status === 400) {
      Swal.fire({
        background: '#86198f',
        color: '#fff',
        position: 'center',
        icon: 'error',
        title: `${err.error.message}`,
        showConfirmButton: false,
        timer: 1500,
      });
    }
  }
}
