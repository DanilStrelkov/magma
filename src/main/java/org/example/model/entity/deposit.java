package org.example.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enumerated.status.AccountStatus;
import org.example.model.enumerated.status.ClientStatus;
import org.example.model.enumerated.type.CurrencyType;
import org.example.model.enumerated.type.DepositType;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deposit")
public class deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "money_amount")
    private Long moneyAmount;
    @Builder.Default
    @NotNull
    @Column(name = "currency_type")
    private CurrencyType currencyType=CurrencyType.RUBLE;
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
    @Builder.Default
    @NotNull
    @Column(name = "deposit_type")
    private DepositType depositType=DepositType.REPLENISHABLE;
    @Column(name = "deposit_limit")
    private Float depositLimit;
    @Column(name = "income_date")
    @NotNull
    @Builder.Default
    private LocalDateTime incomeDate = LocalDateTime.now();
    @Column(name = "client_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @NotNull
    private Client client;
    @Column(name = "expire_date")
    @NotNull
    @Builder.Default
    private LocalDateTime expireDate = LocalDateTime.now();


}
