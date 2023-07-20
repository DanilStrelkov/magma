package org.example.mapper;

import org.example.model.dto.request.TransactionRequestDTO;
import org.example.model.dto.response.TransactionResponseDTO;
import org.example.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TransactionMapper {
  TransactionResponseDTO toDto(Transaction transaction);

  Transaction toEntity(TransactionRequestDTO dto);

  List<TransactionResponseDTO> toListDto(List<Transaction> transactions);
}
