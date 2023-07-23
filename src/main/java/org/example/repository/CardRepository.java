package org.example.repository;

import org.example.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
  Optional<Card> getCardByCardNumber(String number);
  Optional<Card> getCardByNumber(String number);
}
