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

enum TransactionStatus {
  ACCEPTED,

  REJECTED,
}

enum TransactionType {
  DEPOSIT,

  TRANSFER,

  WITHDRAW,
}
