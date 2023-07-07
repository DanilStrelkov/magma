package org.example.model.dto.response;

import lombok.Data;
import org.example.model.entity.Client;
import org.example.model.enumerated.term.TermLength;
import org.example.model.enumerated.type.CurrencyType;

@Data
public class CreditResponseDTO {
  private Client client;
  private Long moneyAmount;
  private Double percent;
  private CurrencyType currencyType;
  private TermLength term;
}
