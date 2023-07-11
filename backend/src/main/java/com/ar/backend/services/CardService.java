package com.ar.backend.services;

import com.ar.backend.dtos.MessageDto;
import com.ar.backend.dtos.MoneyTransactionRequestDto;
import com.ar.backend.entities.Account;
import com.ar.backend.entities.Card;
import com.ar.backend.entities.User;
import com.ar.backend.exceptions.GeneralException;
import com.ar.backend.repositories.AccountRepository;
import com.ar.backend.repositories.CardRepository;
import com.ar.backend.services.interfaces.CardServiceInterface;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CardService implements CardServiceInterface {

  private final CardRepository cardRepository;

  private final AccountRepository accountRepository;

  private final AccountService accountService;

  private final TransactionService transactionService;

  ModelMapper mapper = new ModelMapper();


  @Override
  public List<Card.CardDto> getCards(Long accountId) {
    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new GeneralException("No existe la cuenta",
            HttpStatus.NOT_FOUND));
    Set<Card> cards = account.getCards();
    List<Card.CardDto> cardDtos = new ArrayList<>();
    for (Card card : cards) {
      Card.CardDto cardDto = new Card.CardDto();
      BeanUtils.copyProperties(card, cardDto);
      User.UserInfoDto userInfoDto = new User.UserInfoDto();
      BeanUtils.copyProperties(card.getAccount().getUser(), userInfoDto);
      Account.AccountInfoDto accountInfoDto = new Account.AccountInfoDto();
      BeanUtils.copyProperties(card.getAccount(), accountInfoDto);
      accountInfoDto.setUser(userInfoDto);
      cardDto.setAccount(accountInfoDto);
      cardDtos.add(cardDto);
    }
    return cardDtos;
  }

  @Override
  public MessageDto appendCard(Long accountId, Card.CardRequest cardRequest) {
    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new GeneralException("No existe la cuenta",
            HttpStatus.NOT_FOUND));
    if (cardRepository.existsByCardNumber(cardRequest.getCardNumber())) {
      throw new GeneralException("Esta tarjeta ya esta asociada", HttpStatus.BAD_REQUEST);
    }
    if (!validateCard(cardRequest)) {
      throw new GeneralException("Tarjeta invalida", HttpStatus.BAD_REQUEST);
    }
    Set<Card> cards = new HashSet<>();
    Card card = mapper.map(cardRequest, Card.class);
    card.setAccount(account);
    cardRepository.save(card);
    cards.add(card);
    account.setCards(cards);
    accountRepository.save(account);
    return new MessageDto("Tarjeta agregada exitosamente");
  }

  @Override
  public MessageDto depositToWallet(Long cardId, MoneyTransactionRequestDto requestDto) {
    Card card = cardRepository.findById(cardId)
        .orElseThrow(() -> new GeneralException("La tarjeta no existe",
            HttpStatus.NOT_FOUND));
    accountService.deposit(requestDto.getAccountId(), requestDto.getAmount());
    transactionService.deposit(card, requestDto.getAmount());
    return new MessageDto("Operación realizada correctamente.");
  }

  @Override
  public MessageDto toggleActiveCard(Long cardId) {
    Card card = cardRepository.findById(cardId)
        .orElseThrow(() -> new GeneralException("No existe la tarjeta",
            HttpStatus.NOT_FOUND));
    card.setActive(!card.getActive());
    cardRepository.save(card);
    String status = (card.getActive()) ? "Activado" : "Desactivado";
    return new MessageDto("Tarjeta " + status + " con éxito.");
  }


  @Override
  public boolean validateCard(Card.CardRequest cardRequest) {
    return validateCardNumber(cardRequest.getCardNumber())
        && validatecvv(cardRequest.getCvv())
        && validateExpiredDate(cardRequest.getExpiredDate());
  }

  @Override
  public boolean validateCardNumber(String cardNumber) {
    int[] cardInt = new int[cardNumber.length()];

    for (int i = 0; i < cardNumber.length(); i++) {
      cardInt[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
    }

    for (int i = cardInt.length - 2; i >= 0; i = i - 2) {
      int tempValue = cardInt[i];
      tempValue = tempValue * 2;
      if (tempValue > 9) {
        tempValue = tempValue % 10 + 1;
      }
      cardInt[i] = tempValue;
    }

    int total = 0;
    for (int j : cardInt) {
      total += j;
    }
    return total % 10 == 0;
  }

  @Override
  public boolean validateExpiredDate(String date) {
    if (!date.matches("\\d{2}/\\d{2}")) {

      return false;
    }

    String[] piece = date.split("/");
    int month = Integer.parseInt(piece[0]);
    int year = Integer.parseInt(piece[1]) + 2000;
    LocalDate expiredDate = LocalDate.of(year, month, 1)
        .plusMonths(1).minusDays(1);

    return expiredDate.isAfter(LocalDate.now());
  }

  @Override
  public boolean validatecvv(Integer cvv) {
    String cvvString = String.valueOf(cvv);
    return Pattern.matches("\\d{3}", cvvString);
  }
}
