package com.ar.backend.services;

import com.ar.backend.dtos.MessageDto;
import com.ar.backend.dtos.MoneyTransactionRequestDto;
import com.ar.backend.dtos.TransactionDto;
import com.ar.backend.dtos.TransferRequestDto;
import com.ar.backend.dtos.WithdrawDto;
import com.ar.backend.entities.Account;
import com.ar.backend.entities.Card;
import com.ar.backend.entities.User;
import com.ar.backend.exceptions.GeneralException;
import com.ar.backend.repositories.AccountRepository;
import com.ar.backend.repositories.UserRepository;
import com.ar.backend.services.interfaces.AccountServiceInterface;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AccountService implements AccountServiceInterface {

  private final AccountRepository accountRepository;

  private final UserRepository userRepository;

  private final TransactionService transactionService;

  Random random = new Random();

  @Override
  public Account.AccountDto getAccount(Long accountId) {
    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new GeneralException("La cuenta no existe",
            HttpStatus.NOT_FOUND));
    // Mapeo cards
    Set<Card.CardAccountDto> cardAccountDtos = new HashSet<>();
    for (var card : account.getCards()) {
      Card.CardAccountDto cardAccountDto = new Card.CardAccountDto();
      BeanUtils.copyProperties(card, cardAccountDto);
      cardAccountDtos.add(cardAccountDto);
    }
    Set<TransactionDto> transactionDtos = new HashSet<>();
    for (var transaction : account.getTransactions()) {
      transactionService.mapTransactionsToTransactionsDto(transactionDtos, transaction);
    }

    Account.AccountDto accountDto = new Account.AccountDto();
    accountDto.setCards(cardAccountDtos);
    accountDto.setTransactions(transactionDtos);
    BeanUtils.copyProperties(account, accountDto);
    return accountDto;
  }

  @Override
  @Transactional
  public Account createAccount(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new GeneralException("El usuario no existe",
            HttpStatus.NOT_FOUND));
    Account account = Account.builder()
        .user(user)
        .alias(generateAlias(userId))
        .cvu(generateCvu())
        .active(true)
        .balance(BigDecimal.ZERO)
        .build();

    accountRepository.save(account);
    return account;

  }

  @Override
  public MessageDto deposit(Long accountId, BigDecimal money) {
    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new GeneralException("La cuenta no existe",
            HttpStatus.NOT_FOUND));

    BigDecimal newBalance = account.getBalance().add(money);
    account.setBalance(newBalance);
    accountRepository.save(account);
    return new MessageDto("Operacion realizada correctamente");
  }

  @Override
  @Transactional
  public MessageDto transfer(TransferRequestDto requestDto) {
    withdraw(requestDto.getSenderId(), requestDto.getAmount());
    deposit(requestDto.getRecipientId(), requestDto.getAmount());

    Account sender = accountRepository.findById(requestDto.getSenderId())
        .orElseThrow(() -> new GeneralException("La cuenta no existe",
            HttpStatus.NOT_FOUND));
    Account recipient = accountRepository.findById(requestDto.getRecipientId())
        .orElseThrow(() -> new GeneralException("La cuenta no existe",
            HttpStatus.NOT_FOUND));

    transactionService.transfer(sender, recipient, requestDto.getAmount());
    return new MessageDto("Transferencia Exitosa");
  }

  @Override
  public void withdraw(Long accountId, BigDecimal money) {
    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new GeneralException("La cuenta no existe",
            HttpStatus.NOT_FOUND));

    BigDecimal newBalance = account.getBalance().subtract(money);

    if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
      throw new GeneralException("Fondos insuficientes.", HttpStatus.BAD_REQUEST);
    }
    account.setBalance(newBalance);
    accountRepository.save(account);
  }

  @Override
  public WithdrawDto withdrawFunds(MoneyTransactionRequestDto withdrawDto) {
    withdraw(withdrawDto.getAccountId(), withdrawDto.getAmount());
    Account account = accountRepository.findById(
        withdrawDto.getAccountId()).orElseThrow(() -> new GeneralException("La cuenta no existe",
        HttpStatus.NOT_FOUND));
    String withdrawCode = generateWithdrawCode();
    transactionService.withdrawFunds(account, withdrawDto.getAmount(), withdrawCode);
    return WithdrawDto.builder()
        .date(LocalDateTime.now())
        .amount(withdrawDto.getAmount())
        .code(withdrawCode)
        .message("Codigo de extracción generado ingrese su dni en el cajero elija extracción "
            + "con codigo e ingrese el monto y luego el codigo de extracción "
            + "para extraer su dinero.")
        .build();
  }


  @Override
  public String generateAlias(Long userId) {
    List<String> words = new ArrayList<>(List.of("carro", "cuchara", "ventana", "libro",
        "tigre", "pájaro", "elefante", "flores", "computadora", "mar", "jirafa", "castillo", "sol",
        "montaña", "playa", "bosque", "fruta", "amor", "risa", "llave", "lápiz",
        "película", "fiesta", "futbol", "música", "avión", "hamburguesa", "helado",
        "gafas", "camisa", "zapatos", "sombrero", "reloj", "celular", "cielo", "luna",
        "naturaleza", "aventura"));

    Collections.shuffle(words);

    String secondWord = words.get(1) + "0" + userId;
    return String.join(".", words.get(0), secondWord, words.get(2));
  }

  @Override
  public String generateCvu() {

    long lastDigits = random.nextLong(100000000000000000L);

    return String.format("%022d", lastDigits);
  }

  @Override
  public String generateWithdrawCode() {
    int randomNumberCode = random.nextInt(900000000) + 100000000;

    return String.format("%03d %03d %03d",
        randomNumberCode / 1000000 % 1000,
        randomNumberCode / 1000 % 1000,
        randomNumberCode % 1000);
  }

}
