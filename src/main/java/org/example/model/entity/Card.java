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
    @Column(name = "client_id")
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    @Column(name = "account_id")
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    @Column(name = "status")
    @Builder.Default
    @NotNull
    private CardStatus status = CardStatus.FROZEN;
    @Column(name = "type")
    @NotNull
    private CardType type;
}
