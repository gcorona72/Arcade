// src/main/java/org/example/arcade/repository/ResultRepository.java
package org.example.arcade.repository;

import org.example.arcade.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio Spring Data JPA para Result.
 */
@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}
