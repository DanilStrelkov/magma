package org.example.mapper;

import org.example.model.dto.response.CreditResponceDTO;
import org.example.model.entity.Credit;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CreditMapper {
    CreditResponceDTO toDto(Credit credit);
    Credit toEntity(Credit dto);
}
