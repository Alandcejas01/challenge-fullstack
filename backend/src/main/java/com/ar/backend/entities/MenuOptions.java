package com.ar.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MenuOptions {

  @Getter
  @AllArgsConstructor
  public enum Section {
    INICIO("Inicio", 1L),
    TARJETAS("Tarjetas", 2L),
    PRESTAMOS("Prestamos", 3L),
    OPERACIONES("Operaciones", 4L),
    TEOFRECEMOS("Te ofrecemos", 5L),
    SEGUROS("Seguros", 6L),
    PUNTOS("Puntos", 7L),
    AYUDA("Ayuda", 8L),
    CERRAR_SESION("Cerrar Sesion", 9L);

    private String name;
    private Long order;
  }
}
