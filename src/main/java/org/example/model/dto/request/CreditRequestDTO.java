package org.example.model.dto.request;

import lombok.Data;
import org.example.model.entity.Client;
import org.example.model.enumerated.term.TermLength;
import org.example.model.enumerated.type.CurrencyType;

@Data
public class CreditRequestDTO {
  private Client client;
  private int moneyAmount;
  private Double percent;
  private CurrencyType currencyType;
  private TermLength term;
}
