package com.ar.backend.controllers;

import com.ar.backend.dtos.ErrorDto;
import com.ar.backend.dtos.MessageDto;
import com.ar.backend.exceptions.GeneralException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * Controlador de excepciones para los controllers Rest para el manejo de errores en la aplicación.
 */
@RestControllerAdvice
@Component
public class ControllerAdvice {

  @ExceptionHandler(value = GeneralException.class)
  public ResponseEntity<ErrorDto> requestExceptionHandler(GeneralException ex) {
    ErrorDto error = ErrorDto.builder().message(ex.getMessage()).build();
    return new ResponseEntity<>(error, ex.getStatus());
  }

  /**
   * Este metodo maneja excepciones de validación y
   * devuelve un mapa de errores que contiene información sobre qué campos
   * fallaron la validación.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

  /**
   * Controlador de excepcion para el status code 404.
   */
  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<?> handleNotfoundException(NoHandlerFoundException ex) {
    return ResponseEntity
        .status(ex.getStatusCode())
        .body(new MessageDto(
            "Lo siento, no se pudo encontrar el recurso solicitado."));
  }
}
