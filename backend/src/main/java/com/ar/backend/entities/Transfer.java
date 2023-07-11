package com.ar.backend.entities;

import com.ar.backend.enums.TransactionStatus;
import com.ar.backend.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("TRANSFER")
public class Transfer extends Transaction  {

  @ManyToOne(fetch = FetchType.LAZY)
  private Account sender;

  @ManyToOne(fetch = FetchType.LAZY)
  private Account recipient;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TransferDto {

    private Long id;
    private Account.AccountInfoDto account;
    private Long transactionCode;
    private TransactionType transactionType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private BigDecimal amount;
    private TransactionStatus transactionStatus;
    private Account.AccountInfoDto sender;
    private Account.AccountInfoDto recipient;
  }
}
