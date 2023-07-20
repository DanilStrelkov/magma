package org.example.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enumerated.status.AccountStatus;
import org.example.model.enumerated.type.CurrencyType;

import java.time.LocalDateTime;

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
    private int moneyAmount;
    @Column(name = "secret_word")
    @NotBlank
    @NotNull
    private String secretWord;
    @Column(name = "account_status")
    @Builder.Default
    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.CLOSED;
    @Column(name = "creation_date")
    @NotNull
    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now();
    @Column(name = "deposit_limit")
    private int depositLimit;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @NotNull
    private Client client;
    @Column(name = "currency_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    @Column(name = "deleted")
    @NotNull
    @Builder.Default
    private Boolean isDeleted = false;
    @Column(name = "number")
    @NotNull
    private String number;

}
