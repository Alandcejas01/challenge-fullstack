package com.ar.backend.controllers;

import com.ar.backend.dtos.MessageDto;
import com.ar.backend.dtos.MoneyTransactionRequestDto;
import com.ar.backend.entities.Card;
import com.ar.backend.services.CardService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
public class CardController {

  private final CardService cardService;


  @GetMapping("/cards/{accountId}")
  public ResponseEntity<List<Card.CardDto>> getCards(@PathVariable Long accountId) {
    List<Card.CardDto> response = cardService.getCards(accountId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/appendCard/{accountId}")
  public ResponseEntity<MessageDto> appendCard(
      @PathVariable Long accountId,
      @Valid @RequestBody Card.CardRequest cardRequest) {
    MessageDto response = cardService.appendCard(accountId, cardRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PostMapping("/deposit/{cardId}")
  public ResponseEntity<MessageDto> depositToWallet(
      @PathVariable Long cardId,
      @Valid @RequestBody MoneyTransactionRequestDto withdrawDto) {
    MessageDto response = cardService.depositToWallet(cardId, withdrawDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @DeleteMapping("/toggleActive/{cardId}")
  public ResponseEntity<MessageDto> deleteById(@PathVariable Long cardId) {
    MessageDto response = cardService.toggleActiveCard(cardId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
