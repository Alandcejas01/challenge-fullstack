import { Component, OnInit } from '@angular/core';
import {
  UntypedFormControl,
  UntypedFormGroup,
  Validators,
} from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent implements OnInit {
  error: boolean = false;
  formLogin: UntypedFormGroup = new UntypedFormGroup({});

  constructor(
    private authService: AuthService,
    private cookie: CookieService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.formLogin = new UntypedFormGroup({
      dni: new UntypedFormControl('', [
        Validators.required,
        Validators.minLength(8)
      ]),
      password: new UntypedFormControl('', [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(16),
      ]),
    });
  }

  sendLogin(): void {
    const { dni, password } = this.formLogin.value;
    this.authService.login(dni, password).subscribe({
      next: responseOk => {
        console.log('Sesion iniciada correctamente', responseOk);
        this.toastSigIn();
        const { token, data } = responseOk;
        this.cookie.set('token', token, 1, '/');
        this.router.navigate(['/', 'home']);
      },
      error: err => {
        console.log(err);
        this.error = true;
        this.cookie.delete('token', '/');
        console.log('en teoria elimine el token');
      },
    });
  }

  toastSigIn() {
    const sigIn = Swal.mixin({
      toast: true,
      position: 'top',
      showConfirmButton: false,
      timer: 1500,
      timerProgressBar: true,
      didOpen: toast => {
        toast.addEventListener('mouseenter', Swal.stopTimer);
        toast.addEventListener('mouseleave', Swal.resumeTimer);
      },
    });

    sigIn.fire({
      icon: 'success',
      title: 'Sesión iniciada',
      background: '#86198f',
      color: '#fff',
    });
  }
}
