package com.ar.backend.entities;

import com.ar.backend.enums.TransactionStatus;
import com.ar.backend.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("WITHDRAW")
public class Withdraw extends Transaction {

  private String withdrawCode;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class WithdrawDto {

    private Long id;
    private Account.AccountInfoDto account;
    private Long transactionCode;
    private TransactionType transactionType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private BigDecimal amount;
    private TransactionStatus transactionStatus;
    private String withdrawCode;
  }
}
