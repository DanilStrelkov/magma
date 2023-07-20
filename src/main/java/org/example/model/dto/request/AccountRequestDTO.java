package org.example.model.dto.request;

import lombok.Data;
import org.example.model.entity.Card;
import org.example.model.entity.Client;
import org.example.model.enumerated.status.AccountStatus;
import org.example.model.enumerated.type.CurrencyType;

import java.util.List;

@Data
public class AccountRequestDTO {
  private int moneyAmount;
  private String secretWord;
  private AccountStatus status;
  private int depositLimit;
  private Client client;
  private List<Card> card;
  private CurrencyType currencyType;
}
