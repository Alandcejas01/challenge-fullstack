package com.ar.backend.controllers;

import com.ar.backend.dtos.TransactionDto;
import com.ar.backend.services.TransactionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;

  @GetMapping("/transactions/{accountId}")
  public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable Long accountId) {
    List<TransactionDto> response = transactionService.getTransactions(accountId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/last/{accountId}")
  public ResponseEntity<List<TransactionDto>> getLastTransactions(@PathVariable Long accountId) {
    List<TransactionDto> response = transactionService.lastTransactions(accountId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
