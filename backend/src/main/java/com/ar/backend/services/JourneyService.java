package com.ar.backend.services;

import com.ar.backend.dtos.response.JourneyResponse;
import com.ar.backend.entities.Account;
import com.ar.backend.entities.Card;
import com.ar.backend.entities.MenuOptions;
import com.ar.backend.entities.User;
import com.ar.backend.exceptions.GeneralException;
import com.ar.backend.repositories.AccountRepository;
import com.ar.backend.services.interfaces.JourneyServiceInterface;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JourneyService implements JourneyServiceInterface {

  private final AccountRepository accountRepository;

  @Override
  public List<String> getMenus() {
    List<String> sectionNames = new ArrayList<>();
    for (MenuOptions.Section section : MenuOptions.Section.values()) {
      sectionNames.add(section.getName());
    }
    return sectionNames;
  }

  @Override
  public JourneyResponse getJourney(Long accountId) {
    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new GeneralException("No existe la cuenta",
            HttpStatus.NOT_FOUND));
    List<Card.CardAccountDto> cardAccountDtos = new ArrayList<>();
    for (Card card : account.getCards()) {
      Card.CardAccountDto cardAccountDto = new Card.CardAccountDto();
      BeanUtils.copyProperties(card, cardAccountDto);
      cardAccountDtos.add(cardAccountDto);
    }
    User.UserInfoDto userInfoDto = new User.UserInfoDto();
    BeanUtils.copyProperties(account.getUser(), userInfoDto);
    Account.AccountInfoDto accountInfoDto = new Account.AccountInfoDto();
    BeanUtils.copyProperties(account, accountInfoDto);
    accountInfoDto.setUser(userInfoDto);
    return JourneyResponse.builder()
        .balance(account.getBalance())
        .menus(getMenus())
        .cards(cardAccountDtos)
        .account(accountInfoDto)
        .build();
  }

}
