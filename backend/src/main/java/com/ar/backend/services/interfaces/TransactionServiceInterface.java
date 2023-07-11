package com.ar.backend.services.interfaces;


import com.ar.backend.dtos.TransactionDto;
import com.ar.backend.entities.Account;
import com.ar.backend.entities.Card;
import java.math.BigDecimal;
import java.util.List;

public interface TransactionServiceInterface {

  List<TransactionDto> getTransactions(Long accountId);

  void transfer(Account sender, Account recipient, BigDecimal amount);

  void deposit(Card card, BigDecimal amount);

  void withdrawFunds(Account account, BigDecimal amount, String withdrawCode);

  Long generateTransactionCode();

  List<TransactionDto> lastTransactions(Long accountId);
}
