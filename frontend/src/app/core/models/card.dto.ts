import { AccountInfoDto } from './account.dto';

export interface CardDto {
  id: number;
  account: AccountInfoDto;
  cardName: string;
  cardNumber: string;
  expiredDate: string;
  cardOwner: string;
  bank: string;
  cvv: number;
  cbu: string;
}

export interface CardRequest {
  cardName: string;
  cardNumber: string;
  expiredDate: string;
  cardOwner: string;
  bank: string;
  cvv: number;
  cbu: string;
}

export interface CardAccountDto {
  id: number;
  cardName: string;
  cardNumber: string;
  expiredDate: string;
  cardOwner: string;
  bank: string;
  cvv: number;
  cbu: string;
}

export interface CardDepositDto {
  cardOwner: string;
  bank: string;
  cbu: string;
}
