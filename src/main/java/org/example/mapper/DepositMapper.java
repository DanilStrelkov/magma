package org.example.mapper;

import org.example.model.dto.request.DepositRequestDTO;
import org.example.model.dto.response.DepositResponseDTO;
import org.example.model.entity.Deposit;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface DepositMapper {
  DepositResponseDTO toDto(Deposit deposit);

  Deposit toEntity(DepositRequestDTO dto);

  List<DepositResponseDTO> toListDto(List<Deposit> deposit);
}
