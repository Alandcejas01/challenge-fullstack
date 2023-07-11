package com.ar.backend.services;

import com.ar.backend.dtos.TransactionDto;
import com.ar.backend.entities.Account;
import com.ar.backend.entities.Card;
import com.ar.backend.entities.Deposit;
import com.ar.backend.entities.Transaction;
import com.ar.backend.entities.Transfer;
import com.ar.backend.entities.User;
import com.ar.backend.entities.Withdraw;
import com.ar.backend.enums.TransactionStatus;
import com.ar.backend.enums.TransactionType;
import com.ar.backend.repositories.TransactionRepository;
import com.ar.backend.services.interfaces.TransactionServiceInterface;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class TransactionService implements TransactionServiceInterface {

  private final TransactionRepository transactionRepository;

  @Override
  public List<TransactionDto> getTransactions(Long accountId) {
    List<Transaction> transactions = transactionRepository.findAll();
    List<TransactionDto> transactionDtos = new ArrayList<>();
    for (Transaction transaction : transactions) {
      mapTransactionsToTransactionsDto(transactionDtos, transaction);
    }
    return transactionDtos;
  }

  @Override
  public void transfer(Account sender, Account recipient, BigDecimal amount) {
    Transfer transfer = new Transfer();
    transfer.setAccount(sender);
    transfer.setTransactionCode(generateTransactionCode());
    transfer.setDate(LocalDateTime.now());
    transfer.setAmount(amount);
    transfer.setSender(sender);
    transfer.setRecipient(recipient);
    transfer.setType(TransactionType.TRANSFER);
    transfer.setTransactionStatus(TransactionStatus.ACCEPTED);
    transactionRepository.save(transfer);
  }

  @Override
  public void deposit(Card card, BigDecimal amount) {
    Deposit deposit = new Deposit();
    deposit.setAccount(card.getAccount());
    deposit.setTransactionCode(generateTransactionCode());
    deposit.setDate(LocalDateTime.now());
    deposit.setTransactionStatus(TransactionStatus.ACCEPTED);
    deposit.setAmount(amount);
    deposit.setCard(card);
    deposit.setType(TransactionType.DEPOSIT);
    transactionRepository.save(deposit);
  }

  @Override
  public void withdrawFunds(Account account, BigDecimal amount, String withdrawCode) {
    Withdraw withdraw = new Withdraw();
    withdraw.setAccount(account);
    withdraw.setTransactionCode(generateTransactionCode());
    withdraw.setDate(LocalDateTime.now());
    withdraw.setTransactionStatus(TransactionStatus.ACCEPTED);
    withdraw.setAmount(amount);
    withdraw.setWithdrawCode(withdrawCode);
    withdraw.setType(TransactionType.WITHDRAW);
    transactionRepository.save(withdraw);
  }

  @Override
  public Long generateTransactionCode() {
    Random random = new Random();

    long firstNumbers = 2107L;

    return firstNumbers * 100000000L + random.nextInt(100000000);
  }

  @Override
  public List<TransactionDto> lastTransactions(Long accountId) {
    List<Transaction> transactions = transactionRepository.findLastTransactions(accountId);
    List<TransactionDto> transactionDtos = new ArrayList<>();
    for (var transaction : transactions) {
      mapTransactionsToTransactionsDto(transactionDtos, transaction);
    }
    return transactionDtos;
  }

  public void mapTransactionsToTransactionsDto(
      List<TransactionDto> transactionDtos, Transaction transaction) {
    mapTransactionsToDto(transactionDtos, transaction);
  }

  public void mapTransactionsToTransactionsDto(
      Set<TransactionDto> transactionDtos, Transaction transaction) {
    mapTransactionsToDto(transactionDtos, transaction);
  }

  private static void mapTransactionsToDto(
      Collection<TransactionDto> transactionDtos, Transaction transaction) {
    // Mapeo accountDto

    Account.AccountInfoDto transactionAccountDto = new Account.AccountInfoDto();
    BeanUtils.copyProperties(transaction.getAccount(), transactionAccountDto);
    // Mapeo accountUser
    User.UserInfoDto userInfoDto = new User.UserInfoDto();
    BeanUtils.copyProperties(transaction.getAccount().getUser(), userInfoDto);
    transactionAccountDto.setUser(userInfoDto);

    TransactionDto transactionDto = new TransactionDto();
    transactionDto.setAccount(transactionAccountDto);
    BeanUtils.copyProperties(transaction, transactionDto);
    transactionDtos.add(transactionDto);
  }

}
