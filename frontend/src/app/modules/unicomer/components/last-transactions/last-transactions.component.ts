import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { TransactionService } from '../../services/transaction.service';
import {
  TransactionDto,
  TransactionStatus,
} from 'src/app/core/models/transaction.dto';
import { ErrorhandlesService } from 'src/app/core/utils/errorhandles.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { ModalMoreInfoComponent } from './modal-more-info/modal-more-info.component';

@Component({
  selector: 'app-last-transactions',
  templateUrl: './last-transactions.component.html',
  styleUrls: ['./last-transactions.component.css'],
})
export class LastTransactionsComponent implements AfterViewInit {
  displayedColumns: string[] = [
    'accountAndType',
    'amount',
    'date',
    'transactionStatus',
    'moreInfo',
  ];
  dataSource = new MatTableDataSource<TransactionDto>([]);
  classTransactionStatus = {
    [TransactionStatus.ACCEPTED]: 'text-green-300',
    [TransactionStatus.REJECTED]: 'text-red-300',
  };

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private transactionService: TransactionService,
    private errorHandle: ErrorhandlesService,
    public dialog: MatDialog
  ) {}

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.getLastTransactions();
  }

  getLastTransactions(): void {
    this.transactionService.getLastTransactions(1).subscribe({
      next: (transactions: TransactionDto[]) => {
        console.log(transactions);
        this.dataSource.data = transactions;
      },
      error: err => this.errorHandle.apiError,
    });
  }

  openDialog(transaction: TransactionDto) {
    this.dialog.open(ModalMoreInfoComponent, {
      data: transaction,
    });
  }

  assertItemType(item: TransactionDto): TransactionDto {
    return item;
  }
}
