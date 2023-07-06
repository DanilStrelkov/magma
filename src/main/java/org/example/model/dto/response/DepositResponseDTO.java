package org.example.model.dto.response;

import lombok.Data;
import org.example.model.enumerated.status.DepositStatus;
import org.example.model.enumerated.type.CurrencyType;
import org.example.model.enumerated.type.DepositType;

import java.time.LocalDateTime;

@Data
public class DepositResponseDTO {
    private Long moneyAmount;
    private CurrencyType currencyType;
    private DepositStatus depositStatus ;
    private LocalDateTime creationDate ;
    private DepositType depositType;
    private Float depositLimit;
    private LocalDateTime incomeDate;
    private LocalDateTime expireDate;
}
