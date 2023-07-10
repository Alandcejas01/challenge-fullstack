package com.ar.backend.repositories;

import com.ar.backend.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * Interfaz para User que extiende de JpaRepository para heredar las querys de JPA.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByDni(String dni);
}
