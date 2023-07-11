package com.ar.backend.dtos;

import com.ar.backend.entities.Account;
import com.ar.backend.enums.TransactionStatus;
import com.ar.backend.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

  private Long id;
  private Account.AccountInfoDto account;
  private Long transactionCode;
  private TransactionType type;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;
  private BigDecimal amount;
  private TransactionStatus transactionStatus;
}