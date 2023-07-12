package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.CardRequestDTO;
import org.example.model.dto.response.CardResponseDTO;
import org.example.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
@AllArgsConstructor
public class CardController {
  private CardService cardService;

  @PostMapping
  @RequestMapping("/cash/get")
  public String getCash(@RequestBody CardRequestDTO cardRequestDTO) {
    return cardService.getCash(cardRequestDTO.getCardNumber(), cardRequestDTO.getCvv(), cardRequestDTO.getMoneyAmount());
  }

  @PostMapping
  @RequestMapping("/cash/put")
  public String putCash(@RequestBody CardRequestDTO cardRequestDTO) {
    return cardService.putCash(cardRequestDTO.getCardNumber(), cardRequestDTO.getCvv(), cardRequestDTO.getMoneyAmount());
  }


  @PostMapping
  public ResponseEntity<CardResponseDTO> create(@RequestBody CardRequestDTO cardRequestDTO) {
    return new ResponseEntity<>(cardService.create(cardRequestDTO), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<CardResponseDTO>> readAll() {
    return new ResponseEntity<>(cardService.readAll(), HttpStatus.OK);

  }

  @GetMapping("/{id}")
  public ResponseEntity<CardResponseDTO> readById(@PathVariable Long id) {
    return new ResponseEntity<>(cardService.readById(id), HttpStatus.OK);

  }

  @PutMapping("/{id}")
  public ResponseEntity<CardResponseDTO> update(@PathVariable Long id, @RequestBody CardRequestDTO cardRequestDTO) {
    return new ResponseEntity<>(cardService.update(id,cardRequestDTO), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public HttpStatus delete(@PathVariable Long id) {
    return cardService.delete(id);
  }
}
