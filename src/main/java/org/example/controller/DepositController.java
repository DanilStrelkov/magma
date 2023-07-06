package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.DepositRequestDTO;
import org.example.model.dto.response.DepositResponseDTO;
import org.example.model.enumerated.status.DepositStatus;
import org.example.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/deposit")
@AllArgsConstructor
public class DepositController {
    private DepositService depositService;
    @PostMapping
    public ResponseEntity<DepositResponseDTO> create(@RequestBody DepositRequestDTO dto){
        return new ResponseEntity<>(depositService.create(dto), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<DepositResponseDTO>> readAll(){
        return new ResponseEntity<>(depositService.readAll(),HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<DepositResponseDTO> readByid(@PathVariable Long id){
        return new ResponseEntity<>(depositService.readById(id),HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<DepositResponseDTO> update(@RequestBody DepositRequestDTO depositRequestDTO){
        return new ResponseEntity<>(depositService.update(depositRequestDTO),HttpStatus.OK);
    }
    @PutMapping("/{id}/{depositStatus}")
    public HttpStatus updateStatus(@PathVariable Long id, @PathVariable DepositStatus depositStatus){
        return depositService.updateStatus(id,depositStatus);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        return depositService.delete(id);
    }
}
