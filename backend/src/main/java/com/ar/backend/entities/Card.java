package com.ar.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "cards")
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Account account;

  private String cardName;

  private String cardNumber;

  private String expiredDate;

  private String cardOwner;

  private String bank;

  private Integer cvv;

  private String cbu;

  @Column(columnDefinition = "boolean default true")
  private Boolean active = true;



  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CardDto {
    private Long id;
    private Account.AccountInfoDto account;
    private String cardName;
    private String cardNumber;
    private String expiredDate;
    private String cardOwner;
    private String bank;
    private Integer cvv;
    private String cbu;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CardRequest {
    @NotBlank(message = "El nombre de la tarjeta es requerido")
    private String cardName;
    @NotBlank(message = "El numero de la tarjeta es requerido")
    private String cardNumber;
    @NotBlank(message = "La fecha de vencimiento de la tarjeta es requerida")
    private String expiredDate;
    @NotBlank(message = "El nombre del dueño de la tarjeta es requerido")
    private String cardOwner;
    @NotBlank(message = "El banco de la tarjeta es requerido")
    private String bank;
    @NotNull(message = "El cvv de la tarjeta es requerido")
    private Integer cvv;
    @NotBlank(message = "El cbu de la tarjeta es requerido")
    private String cbu;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CardAccountDto {
    private Long id;
    private String cardName;
    private String cardNumber;
    private String expiredDate;
    private String cardOwner;
    private String bank;
    private Integer cvv;
    private String cbu;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CardDepositDto {
    private String cardOwner;
    private String bank;
    private String cbu;
  }
}
