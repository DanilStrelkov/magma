package org.example.controller;

import org.example.model.entity.Credit;
import org.example.model.enumerated.status.CreditStatus;
import org.example.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/credits")
public class CreditController {
    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping
    public ResponseEntity<Credit> createCredit(@RequestBody Credit credit) {
        Credit createdCredit = creditService.create(credit);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCredit);
    }

    @GetMapping
    public ResponseEntity<List<Credit>> getAllCredits() {
        List<Credit> credits = creditService.readAll();
        return ResponseEntity.ok(credits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credit> getCreditById(@PathVariable Long id) {
        Optional<Credit> optionalCredit = creditService.readById(id);
        if (optionalCredit.isPresent()) {
            Credit credit = optionalCredit.get();
            return ResponseEntity.ok(credit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Credit> updateCredit(@PathVariable Long id, @RequestBody Credit credit) {
        Optional<Credit> optionalCredit = creditService.readById(id);
        if (optionalCredit.isPresent()) {
            credit.setId(id);
            Credit updatedCredit = creditService.update(credit);
            return ResponseEntity.ok(updatedCredit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Credit> updateCreditStatus(@PathVariable Long id, @RequestBody CreditStatus creditStatus) {
        Optional<Credit> optionalCredit = creditService.readById(id);
        if (optionalCredit.isPresent()) {
            Credit credit = optionalCredit.get();
            credit.setCreditStatus(creditStatus);
            Credit updatedCredit = creditService.update(credit);
            return ResponseEntity.ok(updatedCredit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
