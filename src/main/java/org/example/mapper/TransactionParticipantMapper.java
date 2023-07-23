package org.example.mapper;

import org.example.model.dto.request.TransactionParticipantRequestDTO;
import org.example.model.TransactionParticipant;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TransactionParticipantMapper {
  TransactionParticipant toEntity(TransactionParticipantRequestDTO dto);
}
