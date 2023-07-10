package com.ar.backend.services.interfaces;

import com.ar.backend.entities.User;
import java.util.Optional;



/**
 * Interfaz con los metodos a implementar en el Userservice.
 */
public interface UserServiceInterface {

  void save(User.UserAuthentication userDto);

  Optional<User> findByDni(String dni);

  User.UserDto findById(Long userId);

  User.UserDto getByDni(String dni);
}
