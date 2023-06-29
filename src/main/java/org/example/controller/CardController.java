package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.CardRequestDTO;
import org.example.model.entity.Card;
import org.example.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/card")
@AllArgsConstructor
public class CardController {
    private CardService cardService;
    @PostMapping
    @RequestMapping("/cash/get")
    public String getCash (@RequestBody CardRequestDTO dto) {
        return cardService.getCash(dto.getCardNumber(),dto.getCvv(),dto.getMoneyAmount());
    }
    @PostMapping
    @RequestMapping("/cash/put")
    public String putCash (@RequestBody CardRequestDTO dto) {
        return cardService.putCash(dto.getCardNumber(),dto.getCvv(),dto.getMoneyAmount());
    }


    @PostMapping
    public ResponseEntity<Card> create(@RequestBody CardRequestDTO dto) {
        return new ResponseEntity<>(cardService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Card>> readAll() {
        return new ResponseEntity<>(cardService.readAll(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Card>> readById(@PathVariable Long id) {
        return new ResponseEntity<>(cardService.readById(id), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Card> update(@RequestBody Card card) {
        return new ResponseEntity<>(cardService.update(card), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        cardService.delete(id);
        return HttpStatus.OK;
    }
}
