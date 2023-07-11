package com.ar.backend.services.interfaces;

import com.ar.backend.dtos.MessageDto;
import com.ar.backend.dtos.MoneyTransactionRequestDto;
import com.ar.backend.dtos.TransferRequestDto;
import com.ar.backend.dtos.WithdrawDto;
import com.ar.backend.entities.Account;
import java.math.BigDecimal;



/**
 * Interfaz con los metodos a implementar en el AccountService.
 */
public interface AccountServiceInterface {

  Account.AccountDto getAccount(Long accountId);

  Account createAccount(Long userId);

  MessageDto deposit(Long accountId, BigDecimal money);

  MessageDto transfer(TransferRequestDto requestDto);

  void withdraw(Long accountId, BigDecimal money);

  WithdrawDto withdrawFunds(MoneyTransactionRequestDto withdrawDto);

  String generateAlias(Long userId);

  String generateCvu();

  String generateWithdrawCode();
}
