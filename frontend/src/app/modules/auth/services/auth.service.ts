import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly URL = environment.api;
  constructor(private http: HttpClient) {}

  login(dni: string, password: string): Observable<any> {
    const body = {
      dni,
      password,
    };
    return this.http.post(`${this.URL}/auth/login`, body);
  }
}
