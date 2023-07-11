package com.ar.backend.repositories;

import com.ar.backend.entities.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  @Query(value =
      "SELECT * FROM Transaction t WHERE t.account_id = :userId ORDER BY t.date DESC LIMIT 10 ",
      nativeQuery = true)
  List<Transaction> findLastTransactions(Long userId);
}
