package com.ar.backend.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración para el cors.
 */
@Configuration
public class CorsConfig {

  /**
   * Origenes permitidos y metodos.
   */
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@NotNull CorsRegistry registry) {
        registry.addMapping("/api/v1/**")
            .allowedOrigins("https://challenge-fullstack-d4c5c.web.app")
            .allowedMethods("*");
      }
    };
  }
}
