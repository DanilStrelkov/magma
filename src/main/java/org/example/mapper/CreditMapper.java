package org.example.mapper;

import org.example.model.dto.request.CreditRequestDTO;
import org.example.model.dto.response.CreditResponseDTO;
import org.example.model.entity.Credit;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CreditMapper {
  CreditResponseDTO toDto(Credit credit);
  Credit toEntity(CreditRequestDTO dto);

  List<CreditResponseDTO> toListDto(List<Credit> all);
}
