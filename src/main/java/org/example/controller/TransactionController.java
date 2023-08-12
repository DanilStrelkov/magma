package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.TransactionParticipantRequestDTO;
import org.example.model.dto.response.ClientResponseDTO;
import org.example.model.enumerated.type.ObjectType;
import org.example.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {
  private TransactionService transactionService;

  @PostMapping
  public ResponseEntity<ClientResponseDTO> create(@RequestParam ObjectType objectType1, @RequestParam ObjectType objectType2,@RequestParam String number1,@RequestParam String number2, @RequestParam(name = "money") int moneyAmount) {
    return new ResponseEntity<>(transactionService.moneyTransfer(transactionService.paramsToDTO(number1,objectType1),transactionService.paramsToDTO(number2,objectType2),moneyAmount));
  }
}
