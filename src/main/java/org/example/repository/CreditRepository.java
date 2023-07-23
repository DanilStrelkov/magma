package org.example.repository;

import org.example.model.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long>{
  Optional<Credit> getCreditByNumber(String number);
}
