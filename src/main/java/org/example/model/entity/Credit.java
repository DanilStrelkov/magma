package org.example.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enumerated.term.TermType;
import org.example.model.enumerated.type.CurrencyType;

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

    @Column(name = "term_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private TermType term;
}
