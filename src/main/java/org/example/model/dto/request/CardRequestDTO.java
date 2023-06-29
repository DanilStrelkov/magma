package org.example.model.dto.request;

import lombok.Builder;
import lombok.Data;
import org.example.model.entity.Account;
import org.example.model.entity.Client;
import org.example.model.enumerated.status.CardStatus;
import org.example.model.enumerated.type.CardType;

import java.util.Date;

@Data
@Builder
public class CardRequestDTO {
    private String cardNumber;
    private Long moneyAmount;
    private String cvv;
    private Date expireDate;
    private String cardHolder;
    private Client client;
    private Account account;
    private CardStatus status;
    private CardType type;
}
