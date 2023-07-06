package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.AccountRequestDTO;
import org.example.model.dto.response.AccountResponseDTO;
import org.example.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO accountRequestDTO) {
        return new ResponseEntity<>(accountService.create(accountRequestDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> readAll() {
        return new ResponseEntity<>(accountService.readAll(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> readById(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.readById(id), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<AccountResponseDTO> update(@RequestBody AccountRequestDTO accountRequestDTO) {
        return new ResponseEntity<>(accountService.update(accountRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        return accountService.delete(id);
    }
}
