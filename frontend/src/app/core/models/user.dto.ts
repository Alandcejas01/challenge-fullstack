import { AccountDto } from './account.dto';

export interface UserDto {
  id: number;
  fullname: string;
  email: string;
  dni: string;
  account: AccountDto;
}

export interface UserInfoDto {
  fullname: string;
  dni: string;
}
