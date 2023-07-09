package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.CreditCardRequestDTO;
import org.example.model.dto.response.CreditCardResponseDTO;
import org.example.service.CreditCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit_cards")
@AllArgsConstructor
public class CreditCardController {
  private final CreditCardService creditCardService;

  @PostMapping
  public ResponseEntity<CreditCardResponseDTO> create(@RequestBody CreditCardRequestDTO creditCardRequestDTO) {
    return new ResponseEntity<>(creditCardService.create(creditCardRequestDTO), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<CreditCardResponseDTO>> readAll() {
    return new ResponseEntity<>(creditCardService.readAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CreditCardResponseDTO> readById(@PathVariable Long id) {
    return new ResponseEntity<>(creditCardService.readById(id), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<CreditCardResponseDTO> update(@RequestBody CreditCardRequestDTO creditCardRequestDTO) {
    return new ResponseEntity<>(creditCardService.update(creditCardRequestDTO), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public HttpStatus delete(@PathVariable Long id) {
    return creditCardService.delete(id);
  }
}
