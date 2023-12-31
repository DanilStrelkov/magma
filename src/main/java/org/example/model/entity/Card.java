package org.example.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enumerated.status.CardStatus;
import org.example.model.enumerated.type.CardType;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "card")
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "card_number")
  @NotNull
  @NotBlank
  private String cardNumber;
  @Column(name = "money_amount")
  private int moneyAmount;
  @Column(name = "cvv")
  @NotNull
  @NotBlank
  private String cvv;
  @Column(name = "expire_date")
  @NotNull
  private LocalDateTime expireDate;
  @Column(name = "card_holder")
  @NotNull
  @NotBlank
  private String cardHolder;
  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client client;
  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  private Account account;
  @Column(name = "card_status")
  @Builder.Default
  @NotNull
  @Enumerated(EnumType.STRING)
  private CardStatus status = CardStatus.FROZEN;
  @Column(name = "card_type")
  @NotNull
  @Enumerated(EnumType.STRING)
  private CardType type;
  @Column(name = "deleted")
  @NotNull
  @Builder.Default
  private Boolean isDeleted = false;
  @Column(name = "number")
  @NotNull
  private String number;
}
