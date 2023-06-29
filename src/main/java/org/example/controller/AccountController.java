package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.AccountRequestDTO;
import org.example.model.entity.Account;
import org.example.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody AccountRequestDTO dto) {
        return new ResponseEntity<>(accountService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Account>> readAll() {
        return new ResponseEntity<>(accountService.readAll(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Account>> readById(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.readById(id), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Account> update(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.update(account), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        accountService.delete(id);
        return HttpStatus.OK;
    }
}
