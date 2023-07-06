package org.example.model.dto.request;

import lombok.Data;
import org.example.model.entity.Client;
import org.example.model.enumerated.term.TermType;
import org.example.model.enumerated.type.CurrencyType;

@Data
public class CreditRequestDTO {
    private Long id;
    private Client client;
    private Long moneyAmount;
    private Double percent;
    private CurrencyType currencyType;
    private TermType term;
}
