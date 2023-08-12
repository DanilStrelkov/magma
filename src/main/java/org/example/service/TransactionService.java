package org.example.service;

import lombok.AllArgsConstructor;
import org.example.mapper.TransactionMapper;
import org.example.mapper.TransactionParticipantMapper;
import org.example.model.dto.request.TransactionParticipantRequestDTO;
import org.example.model.dto.response.TransactionResponseDTO;
import org.example.model.entity.Transaction;
import org.example.model.TransactionParticipant;
import org.example.model.enumerated.type.ObjectType;
import org.example.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {
  private final TransactionMapper transactionMapper;
  private final TransactionRepository transactionRepository;
  private final TransactionParticipantMapper transactionParticipantMapper;
  private final TransactionParticipantService transactionParticipantService;

  public TransactionParticipantRequestDTO paramsToDTO(String number, ObjectType objectType){
    TransactionParticipantRequestDTO transactionParticipantRequestDTO = new TransactionParticipantRequestDTO();
    transactionParticipantRequestDTO.setObjectType(objectType);
    transactionParticipantRequestDTO.setNumber(number);
    return transactionParticipantRequestDTO;
  }
  public List<TransactionResponseDTO> readAll() {
    return transactionMapper.toListDto(transactionRepository.findAll());

  }

  public TransactionResponseDTO readById(Long id) {
    Optional<Transaction> transaction = transactionRepository.findById(id);
    return transaction.map(transactionMapper::toDto).orElse(null);
  }

  public HttpStatus moneyTransfer(TransactionParticipantRequestDTO fromDTO, TransactionParticipantRequestDTO toDTO, int moneyAmount) {
    TransactionParticipant from = transactionParticipantService.getMoneyAmount(fromDTO);
    TransactionParticipant to = transactionParticipantService.getMoneyAmount(toDTO);
    if (from.getMoneyAmount() > moneyAmount) {
      from.setMoneyAmount((from.getMoneyAmount() - moneyAmount));
      to.setMoneyAmount((to.getMoneyAmount() + moneyAmount));
      transactionParticipantService.update(from);
      transactionParticipantService.update(to);
      return HttpStatus.OK;
    } else {
      return HttpStatus.I_AM_A_TEAPOT;
    }

  }
}
