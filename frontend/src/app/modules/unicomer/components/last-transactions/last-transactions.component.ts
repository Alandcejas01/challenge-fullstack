import { OnInit, Component } from '@angular/core';
import { TransactionService } from '../../services/transaction.service';
import { TransactionDto } from 'src/app/core/models/transaction.dto';
import { ErrorhandlesService } from 'src/app/core/utils/errorhandles.service';

@Component({
  selector: 'app-last-transactions',
  templateUrl: './last-transactions.component.html',
  styleUrls: ['./last-transactions.component.css'],
})
export class LastTransactionsComponent implements OnInit {
  lastTransactions!: TransactionDto[];

  constructor(
    private transactionService: TransactionService,
    private errorHandle: ErrorhandlesService
  ) {}

  ngOnInit() {
    this.getLastTransactions();
  }

  getLastTransactions(): void {
    this.transactionService.getLastTransactions(1).subscribe({
      next: (transactions: TransactionDto[]) => {
        this.lastTransactions = transactions;
      },
      error: err => this.errorHandle.apiError,
    });
  }
}
