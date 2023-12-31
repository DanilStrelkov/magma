package org.example.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enumerated.status.DepositStatus;
import org.example.model.enumerated.type.CurrencyType;
import org.example.model.enumerated.type.DepositType;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deposit")
public class Deposit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "money_amount")
  private int moneyAmount;
  @Builder.Default
  @NotNull
  @Column(name = "currency_type")
  @Enumerated(EnumType.STRING)
  private CurrencyType currencyType = CurrencyType.RUBLE;
  @Column(name = "deposit_status")
  @Builder.Default
  @NotNull
  @Enumerated(EnumType.STRING)
  private DepositStatus status = DepositStatus.CLOSED;
  @Column(name = "creation_date")
  @NotNull
  @Builder.Default
  private LocalDateTime creationDate = LocalDateTime.now();
  @Builder.Default
  @NotNull
  @Column(name = "deposit_type")
  @Enumerated(EnumType.STRING)
  private DepositType type = DepositType.REPLENISHABLE;
  @Column(name = "deposit_limit")
  private int depositLimit;
  @Column(name = "income")
  @NotNull
  private double income;
  @Column(name = "income_date")
  @NotNull
  @Builder.Default
  private LocalDateTime incomeDate = LocalDateTime.now();
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client client;
  @Column(name = "expire_date")
  @Builder.Default
  private LocalDateTime expireDate = LocalDateTime.now();
  @Column(name = "deleted")
  @NotNull
  @Builder.Default
  private Boolean isDeleted = false;
  @Column(name = "number")
  @NotNull
  private String number;

}
