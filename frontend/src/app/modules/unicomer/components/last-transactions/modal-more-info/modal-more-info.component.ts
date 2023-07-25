import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TransactionDto } from 'src/app/core/models/transaction.dto';

@Component({
  selector: 'app-modal-more-info',
  templateUrl: './modal-more-info.component.html',
  styleUrls: ['./modal-more-info.component.css'],
})
export class ModalMoreInfoComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public transaction: TransactionDto) {}
}
