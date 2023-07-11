package com.ar.backend.entities;

import com.ar.backend.dtos.TransactionDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "account")
  private User user;

  private String cvu;

  private String alias;

  private BigDecimal balance;

  @OneToMany(mappedBy = "account")
  private Set<Card> cards = new HashSet<>();

  @OneToMany(mappedBy = "account")
  private Set<Transaction> transactions = new HashSet<>();

  @Column(columnDefinition = "boolean default true")
  private Boolean active = true;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class AccountDto {
    private Long id;
    private String cvu;
    private String alias;
    private BigDecimal balance;
    private Set<Card.CardAccountDto> cards;
    private Set<TransactionDto> transactions;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class AccountInfoDto {
    private User.UserInfoDto user;
    private String alias;
    private String cvu;
  }
}
