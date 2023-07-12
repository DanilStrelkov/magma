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

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "credit card")
public class CreditCard {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "card_number")
  private String cardNumber;

  @Column(name = "cvv")
  @NotNull
  @NotBlank
  private String cvv;

  @Column(name = "expire_date")
  @NotNull
  private Date expireDate;

  @Column(name = "card_holder")
  @NotNull
  @NotBlank
  private String cardHolder;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client client;

  @Column(name = "status")
  @Builder.Default
  @NotNull
  @Enumerated(EnumType.STRING)
  private CardStatus status = CardStatus.FROZEN;

  @Column(name = "type")
  @NotNull
  @Enumerated(EnumType.STRING)
  private CardType type;
  @Column(name = "deleted")
  @NotNull
  @Builder.Default
  private Boolean isDeleted = false;

}
