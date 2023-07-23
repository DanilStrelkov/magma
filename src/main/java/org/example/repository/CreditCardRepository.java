package org.example.repository;

import org.example.model.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    Optional<CreditCard> getCardByCardNumber(String cardNumber);
    Optional<CreditCard> getCreditCardByNumber(String number);
}
