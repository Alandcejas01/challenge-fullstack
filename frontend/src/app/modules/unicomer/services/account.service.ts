import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JourneyResponse } from 'src/app/core/models/journey-response.dto';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  private readonly URL = environment.api + '/account';
  constructor(private http: HttpClient) {}

  getJourney(accountId: number): Observable<JourneyResponse> {
    return this.http.get<JourneyResponse>(this.URL + `/journey/${accountId}`);
  }
}
