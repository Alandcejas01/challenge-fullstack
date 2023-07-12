import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { TransactionDto } from 'src/app/core/models/transaction.dto';

@Injectable({
  providedIn: 'root',
})
export class TransactionService {
  private readonly URL = environment.api + '/transaction';
  constructor(private http: HttpClient) {}

  getLastTransactions(accounId: number): Observable<TransactionDto[]> {
    return this.http.get<TransactionDto[]>(this.URL + `/last/${accounId}`);
  }
}
