package org.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.TransactionParticipant;
import org.example.model.enumerated.type.CurrencyType;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "money_amount")
  private Long moneyAmount;
  @Column(name = "currency_type")
  @Enumerated(EnumType.STRING)
  private CurrencyType currencyType;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "from")
  private TransactionParticipant from;
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "to", referencedColumnName = "id")
  private TransactionParticipant to;

}
