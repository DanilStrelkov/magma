package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.AccountDTO;
import org.example.model.entity.Account;
import org.example.model.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account create(AccountDTO dto) {
        return accountRepository.save(Account.builder()
                .client(dto.getClient())
                .card(dto.getCard())
                .currencyType(dto.getCurrencyType())
                .moneyAmount(dto.getMoneyAmount())
                .depositLimit(dto.getDepositLimit())
                .creationDate(dto.getCreationDate())
                .secretWord(dto.getSecretWord())
                .status(dto.getStatus())
                .build());
    }

    public List<Account> readAll() {
        return accountRepository.findAll();

    }

    public Optional<Account> readById(Long id) {
        return accountRepository.findById(id);

    }

    public Account update(Account account) {
        return accountRepository.save(account);
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
