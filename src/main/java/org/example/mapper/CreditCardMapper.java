package org.example.mapper;

import org.example.model.dto.request.CreditCardRequestDTO;
import org.example.model.dto.response.CreditCardResponseDTO;
import org.example.model.entity.CreditCard;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CreditCardMapper {
  CreditCardResponseDTO toDto(CreditCard creditCard);

  CreditCard toEntity(CreditCardRequestDTO dto);

  List<CreditCardResponseDTO> toListDto(List<CreditCard> all);
}
