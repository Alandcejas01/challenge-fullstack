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
public class TransferRequestDto {

  @NotNull(message = "El id de la cuenta del remitente es requerido")
  private Long senderId;

  @NotNull(message = "El id de la cuenta del destinatario es requerido")
  private Long recipientId;

  @NotNull(message = "El monto es requerido")
  private BigDecimal amount;
}
