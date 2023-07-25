import { AccountDto } from './account.dto';

export interface UserDto {
  id: number;
  fullName: string;
  email: string;
  dni: string;
  account: AccountDto;
}

export interface UserInfoDto {
  fullName: string;
  dni: string;
}
