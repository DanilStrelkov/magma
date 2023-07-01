package org.example.mapper;

import org.example.model.dto.request.DepositRequestDTO;
import org.example.model.dto.response.DepositResponceDTO;
import org.example.model.entity.Deposit;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DepositMapper {
    DepositResponceDTO toDto(Deposit deposit);

    Deposit toEntity(DepositRequestDTO dto);
}
