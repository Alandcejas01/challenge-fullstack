package com.ar.backend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class WithdrawDto {

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;

  private BigDecimal amount;

  private String code;

  private String message;

}
