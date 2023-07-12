package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.CreditRequestDTO;
import org.example.model.dto.response.CreditResponseDTO;
import org.example.service.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@AllArgsConstructor
public class CreditController {
  private final CreditService creditService;

  @PostMapping
  public ResponseEntity<CreditResponseDTO> create(@RequestBody CreditRequestDTO creditRequestDTO) {
    return new ResponseEntity<>(creditService.create(creditRequestDTO), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<CreditResponseDTO>> readAll() {
    return new ResponseEntity<>(creditService.readAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CreditResponseDTO> readById(@PathVariable Long id) {
    return new ResponseEntity<>(creditService.readById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CreditResponseDTO> update(@PathVariable Long id, @RequestBody CreditRequestDTO creditRequestDTO) {
    return new ResponseEntity<>(creditService.update(id, creditRequestDTO), HttpStatus.OK);
  }


  @DeleteMapping("/{id}")
  public HttpStatus delete(@PathVariable Long id) {
    return creditService.delete(id);
  }
}