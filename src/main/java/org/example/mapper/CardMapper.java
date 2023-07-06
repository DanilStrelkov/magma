package org.example.mapper;

import org.example.model.dto.request.CardRequestDTO;
import org.example.model.dto.response.CardResponseDTO;
import org.example.model.entity.Card;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CardMapper {
    Card toEntity(CardRequestDTO dto);
    CardResponseDTO toDto(Card card);

    List<CardResponseDTO> toListDto(List<Card> cards);
}
