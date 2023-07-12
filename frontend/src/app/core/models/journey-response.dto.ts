import { AccountInfoDto } from './account.dto';
import { CardAccountDto } from './card.dto';

export interface JourneyResponse {
  account: AccountInfoDto;

  balance: number;

  cards: CardAccountDto[];

  menus: string[];
}
