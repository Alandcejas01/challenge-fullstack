import { CardAccountDto } from './card.dto';
import { TransactionDto } from './transaction.dto';
import { UserInfoDto } from './user.dto';

export interface AccountDto {
  id: number;
  cvu: string;
  alias: string;
  balance: number;
  cards: CardAccountDto[];
  transactions: TransactionDto[];
}

export interface AccountInfoDto {
  user: UserInfoDto;
  alias: string;
  cvu: string;
}
