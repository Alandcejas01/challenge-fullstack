package com.ar.backend.entities;

import com.ar.backend.enums.TransactionStatus;
import com.ar.backend.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("DEPOSIT")
public class Deposit extends Transaction {

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Card card;


  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class DepositDto {
    private Long id;
    private Account.AccountInfoDto account;
    private Long transactionCode;
    private TransactionType transactionType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private BigDecimal amount;
    private TransactionStatus transactionStatus;
    private Card.CardDepositDto card;
  }
}
