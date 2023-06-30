package org.example.repository;

import org.example.model.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

}
