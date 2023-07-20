package org.example.model.dto.response;

import lombok.Data;
import org.example.model.entity.Account;
import org.example.model.entity.Client;
import org.example.model.enumerated.status.CardStatus;
import org.example.model.enumerated.type.CardType;

import java.time.LocalDateTime;

@Data
public class CardResponseDTO {
  private String cardNumber;
  private int moneyAmount;
  private String cvv;
  private LocalDateTime expireDate;
  private String cardHolder;
  private Client client;
  private Account account;
  private CardStatus status;
  private CardType type;
}
