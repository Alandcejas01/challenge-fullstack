package com.ar.backend.services;

import com.ar.backend.dtos.TransactionDto;
import com.ar.backend.entities.Account;
import com.ar.backend.entities.Card;
import com.ar.backend.entities.User;
import com.ar.backend.exceptions.GeneralException;
import com.ar.backend.repositories.UserRepository;
import com.ar.backend.services.interfaces.UserServiceInterface;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



/**
 * Servicio que contiene los metodos con la logica del usuario .
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

  private final UserRepository userRepository;

  private final AccountService accountService;

  private final TransactionService transactionService;

  ModelMapper mapper = new ModelMapper();


  @Override
  @Transactional
  public void save(User.UserAuthentication userDto) {
    if (userRepository.findByDni(userDto.getDni()).isPresent()) {
      throw new GeneralException("El usuario ya existe",
          HttpStatus.BAD_REQUEST);
    }

    User userEntity = mapper.map(userDto, User.class);
    userRepository.save(userEntity);

    Account account = accountService.createAccount(userEntity.getId());
    userEntity.setAccount(account);
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

    return mapUserToUserDto(user);
  }


  @Override
  public User.UserDto getByDni(String dni) {
    User user = userRepository.findByDni(dni)
        .orElseThrow(() -> new GeneralException("El usuario no existe",
            HttpStatus.NOT_FOUND));

    return mapUserToUserDto(user);
  }

  public User.UserDto mapUserToUserDto(User user) {
    User.UserDto userDto = new User.UserDto();
    // Mapeo Account
    Account.AccountDto accountDto = new Account.AccountDto();
    BeanUtils.copyProperties(user, userDto);
    BeanUtils.copyProperties(user.getAccount(), accountDto);
    userDto.setAccount(accountDto);
    Set<TransactionDto> transactionDtos = new HashSet<>();
    for (var transaction : user.getAccount().getTransactions()) {
      transactionService.mapTransactionsToTransactionsDto(transactionDtos, transaction);
    }
    // Mapeo cards
    Set<Card.CardAccountDto> cardAccountDtos = new HashSet<>();
    for (var card : user.getAccount().getCards()) {
      Card.CardAccountDto cardAccountDto = new Card.CardAccountDto();
      BeanUtils.copyProperties(card, cardAccountDto);
      cardAccountDtos.add(cardAccountDto);
    }
    userDto.getAccount().setCards(cardAccountDtos);
    userDto.getAccount().setTransactions(transactionDtos);
    // return mapper.map(user, User.UserDto.class);
    return userDto;
  }

}
