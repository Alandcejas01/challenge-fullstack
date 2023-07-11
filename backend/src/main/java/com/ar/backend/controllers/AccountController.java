package com.ar.backend.controllers;

import com.ar.backend.dtos.MessageDto;
import com.ar.backend.dtos.MoneyTransactionRequestDto;
import com.ar.backend.dtos.TransferRequestDto;
import com.ar.backend.dtos.WithdrawDto;
import com.ar.backend.dtos.response.JourneyResponse;
import com.ar.backend.entities.Account;
import com.ar.backend.services.AccountService;
import com.ar.backend.services.JourneyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  private final JourneyService journeyService;

  @GetMapping("/journey/{accountId}")
  public ResponseEntity<JourneyResponse> getJournet(@PathVariable Long accountId) {
    JourneyResponse response = journeyService.getJourney(accountId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/{accountId}")
  public ResponseEntity<Account.AccountDto> getAccount(@PathVariable Long accountId) {
    Account.AccountDto response = accountService.getAccount(accountId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/withdrawFunds")
  public ResponseEntity<WithdrawDto> withdrawFunds(
      @Valid @RequestBody MoneyTransactionRequestDto withdrawDto) {
    WithdrawDto response = accountService.withdrawFunds(withdrawDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("/transfer")
  public ResponseEntity<MessageDto> transfer(
      @Valid @RequestBody TransferRequestDto requestDto) {
    MessageDto response = accountService.transfer(requestDto);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
