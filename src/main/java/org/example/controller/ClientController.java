package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.ClientRequestDTO;
import org.example.model.dto.response.ClientResponseDTO;
import org.example.model.enumerated.status.ClientStatus;
import org.example.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {
  private ClientService clientService;

  @PostMapping
  public ResponseEntity<ClientResponseDTO> create(@RequestBody ClientRequestDTO dto) {
    return new ResponseEntity<>(clientService.create(dto), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<ClientResponseDTO>> readAll() {
    return new ResponseEntity<>(clientService.readAll(), HttpStatus.OK);

  }

  @GetMapping("/{id}")
  public ResponseEntity<ClientResponseDTO> readById(@PathVariable Long id) {
    return new ResponseEntity<>(clientService.readById(id), HttpStatus.OK);

  }

  @PutMapping("/{id}")
  public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id ,@RequestBody ClientRequestDTO clientRequestDTO) {
    return new ResponseEntity<>(clientService.update(id,clientRequestDTO), HttpStatus.OK);
  }

  @PutMapping("/{id}/{clientStatus}")
  public HttpStatus updateStatus(@PathVariable Long id, @PathVariable ClientStatus clientStatus) {
    return clientService.updateStatus(id, clientStatus);
  }

  @DeleteMapping("/{id}")
  public HttpStatus delete(@PathVariable Long id) {
    return clientService.delete(id);
  }
}
