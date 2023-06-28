package org.example.model.repository;

import org.example.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface CardRepository extends JpaRepository<Card,Long> {
}
