package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.DepositRequestDTO;
import org.example.model.entity.Deposit;
import org.example.model.enumerated.status.DepositStatus;
import org.example.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/deposit")
@AllArgsConstructor
public class DepositController {
    private DepositService depositService;
    @PostMapping
    public ResponseEntity<Deposit> create(@RequestBody DepositRequestDTO dto){
        return new ResponseEntity<>(depositService.create(dto), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Deposit>> readAll(){
        return new ResponseEntity<>(depositService.readAll(),HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Deposit>> readByid(@PathVariable Long id){
        return new ResponseEntity<>(depositService.readById(id),HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<Deposit> update(@RequestBody Deposit deposit){
        return new ResponseEntity<>(depositService.update(deposit),HttpStatus.OK);
    }
    @PutMapping("/{id}/{depositStatus}")
    public ResponseEntity<Deposit> updateStatus(@PathVariable Long id, @PathVariable DepositStatus depositStatus){
        return new ResponseEntity<>(depositService.updateStatus(id,depositStatus),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        depositService.delete(id);
        return HttpStatus.OK;
    }
}
