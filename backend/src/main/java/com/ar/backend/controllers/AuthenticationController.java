package com.ar.backend.controllers;

import com.ar.backend.dtos.auth.AuthenticationRequestDto;
import com.ar.backend.dtos.auth.AuthenticationResponseDto;
import com.ar.backend.dtos.auth.RegisterRequestDto;
import com.ar.backend.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller con los endpoints para el registro y login del Usuario.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponseDto> register(
      @Valid @RequestBody RegisterRequestDto request) {
    AuthenticationResponseDto response = authenticationService.register(request);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponseDto> authenticate(
      @Valid @RequestBody AuthenticationRequestDto request) {
    AuthenticationResponseDto response = authenticationService.authenticate(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
