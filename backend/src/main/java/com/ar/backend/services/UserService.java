package com.ar.backend.services;

import com.ar.backend.entities.User;
import com.ar.backend.exceptions.GeneralException;
import com.ar.backend.repositories.UserRepository;
import com.ar.backend.services.interfaces.UserServiceInterface;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



/**
 * Servicio que contiene los metodos con la logica del usuario .
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

  private final UserRepository userRepository;

  ModelMapper mapper = new ModelMapper();

  @Override
  public void save(User.UserAuthentication userDto) {
    if (userRepository.findByDni(userDto.getDni()).isPresent()) {
      throw new GeneralException("El usuario ya existe",
          HttpStatus.BAD_REQUEST);
    }
    User userEntity = mapper.map(userDto, User.class);
    userRepository.save(userEntity);
  }

  @Override
  public Optional<User> findByDni(String dni) {
    return userRepository.findByDni(dni);
  }


  @Override
  public User.UserDto findById(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new GeneralException("El usuario no existe",
            HttpStatus.NOT_FOUND));

    return mapper.map(user, User.UserDto.class);
  }

  @Override
  public User.UserDto getByDni(String dni) {
    User user = userRepository.findByDni(dni)
        .orElseThrow(() -> new GeneralException("El usuario no existe",
            HttpStatus.NOT_FOUND));

    return mapper.map(user, User.UserDto.class);
  }

}
