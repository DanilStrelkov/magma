package org.example.model.dto.request;

import lombok.Data;
import org.example.model.entity.Client;
import org.example.model.enumerated.status.CardStatus;
import org.example.model.enumerated.type.CardType;

import java.util.Date;
@Data
public class CreditCardRequestDTO {
    private Long id;
    private Long cardNumber;
    private int cvv;
    private Date expireDate;
    private String cardHolder;
    private Client client;
    private CardStatus status;
    private CardType type;
}
