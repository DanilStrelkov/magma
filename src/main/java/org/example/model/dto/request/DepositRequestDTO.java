package org.example.model.dto.request;

import lombok.Data;
import org.example.model.entity.Client;
import org.example.model.enumerated.status.DepositStatus;
import org.example.model.enumerated.type.CurrencyType;
import org.example.model.enumerated.type.DepositType;

import java.time.LocalDateTime;

@Data
public class DepositRequestDTO {
  private int moneyAmount;
  private CurrencyType currencyType;
  private String secretWord;
  private DepositStatus depositStatus;
  private LocalDateTime creationDate;
  private DepositType depositType;
  private int depositLimit;
  private Double income;
  private LocalDateTime incomeDate;
  private Client client;
  private LocalDateTime expireDate;
}
