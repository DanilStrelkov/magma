package org.example.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enumerated.status.AccountStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "money_amount")
    private Long moneyAmount;
    @Column(name = "secret_word")
    @NotBlank
    @NotNull
    private String secretWord;
    @Column(name = "status")
    @Builder.Default
    @NotNull
    private AccountStatus status = AccountStatus.CLOSED;
    @Column(name = "creation_date")
    @NotNull
    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now();
    @Column(name = "deposit_limit")
    private Long depositLimit;
    @Column(name = "client_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @NotNull
    private Client client;
    @Column(name = "card_id")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    @NotNull
    private List<Card> card;

}
