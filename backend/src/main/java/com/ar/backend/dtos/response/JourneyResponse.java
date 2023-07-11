package com.ar.backend.dtos.response;

import com.ar.backend.entities.Account;
import com.ar.backend.entities.Card;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JourneyResponse {

  private Account.AccountInfoDto account;

  private BigDecimal balance;

  private List<Card.CardAccountDto> cards;

  private List<String> menus;

}
