package com.ar.backend.repositories;

import com.ar.backend.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

  boolean existsByCardNumber(String cardNumber);
}
