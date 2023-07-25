import { AccountInfoDto } from './account.dto';

export interface TransactionDto {
  id: number;
  account: AccountInfoDto;
  transactionCode: number;
  type: TransactionType;
  date: Date;
  amount: number;
  transactionStatus: TransactionStatus;
}

export enum TransactionStatus {
  ACCEPTED = 'ACCEPTED',

  REJECTED = 'REJECTED',
}

export enum TransactionType {
  DEPOSIT = 'DEPOSIT',

  TRANSFER = 'TRANSFER',

  WITHDRAW = 'WITHDRAW',
}
