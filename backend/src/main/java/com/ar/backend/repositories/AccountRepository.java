package com.ar.backend.repositories;

import com.ar.backend.entities.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  Optional<Account> findByAlias(String alias);

  Optional<Account> findByCvu(String cvu);
}
