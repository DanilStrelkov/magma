package org.example.model.dto.request;

import lombok.Data;
import org.example.model.TransactionParticipant;
import org.example.model.enumerated.type.CurrencyType;

import java.time.LocalDateTime;

@Data
public class TransactionRequestDTO {
  private Long moneyAmount;
  private CurrencyType currencyType;
  private TransactionParticipant from;
  private TransactionParticipant to;
  private LocalDateTime time;
}
