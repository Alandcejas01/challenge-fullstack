package com.ar.backend.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto para el registro JWT.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

  @NotBlank(message = "El nombre completo es requerido")
  private String fullName;
  @NotBlank(message = "El email es requerido")
  private String email;
  @NotBlank(message = "El dni es requerido")
  private String dni;
  @NotBlank(message = "La contraseña es requerida")
  private String password;
}
