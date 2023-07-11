package com.ar.backend.services.interfaces;

import com.ar.backend.dtos.MessageDto;
import com.ar.backend.dtos.MoneyTransactionRequestDto;
import com.ar.backend.entities.Card;
import java.util.List;


public interface CardServiceInterface {

  List<Card.CardDto> getCards(Long accountId);

  MessageDto appendCard(Long accountId, Card.CardRequest cardRequest);

  MessageDto depositToWallet(Long cardId, MoneyTransactionRequestDto requestDto);

  MessageDto toggleActiveCard(Long cardId);

  boolean validateCard(Card.CardRequest cardRequest);

  boolean validateCardNumber(String cardNumber);

  boolean validateExpiredDate(String date);

  boolean validatecvv(Integer cvv);
}
