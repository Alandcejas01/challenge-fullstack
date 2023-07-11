package com.ar.backend.dtos;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoneyTransactionRequestDto {

  @NotNull(message = "El id de la cuenta es requerido")
  private Long accountId;

  @NotNull(message = "El monto es requerido")
  private BigDecimal amount;

}
