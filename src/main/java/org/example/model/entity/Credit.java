package org.example.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enumerated.status.CreditStatus;
import org.example.model.enumerated.term.TermLength;
import org.example.model.enumerated.type.CurrencyType;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "credit")
public class Credit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  @NotNull
  private Client client;

  @Column(name = "credit_status")
  @Builder.Default
  @NotNull
  @Enumerated(EnumType.STRING)
  private CreditStatus creditStatus = CreditStatus.CLOSED;

  @Column(name = "creation_date")
  @NotNull
  @Builder.Default
  private LocalDateTime creationDate = LocalDateTime.now();

  @Column(name = "money_amount")
  @NotNull
  private Long moneyAmount;

  @Column(name = "percent")
  @NotNull
  private Double percent;

  @Column(name = "currency_type")
  @NotNull
  @Enumerated(EnumType.STRING)
  private CurrencyType currencyType;

  @Column(name = "term_length")
  @NotNull
  @Enumerated(EnumType.STRING)
  private TermLength term;
  @Column(name = "deleted")
  @NotNull
  @Builder.Default
  private Boolean isDeleted = false;
}
