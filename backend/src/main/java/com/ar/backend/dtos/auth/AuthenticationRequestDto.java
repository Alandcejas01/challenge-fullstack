package com.ar.backend.dtos.auth;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto para el login JWT.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {
  @NotBlank(message = "El dni es requerido")
  private String dni;
  @NotBlank(message = "La contraseña es requerida")
  private String password;
}
