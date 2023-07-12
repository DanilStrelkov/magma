package org.example.service;

import lombok.AllArgsConstructor;
import org.example.mapper.AccountMapper;
import org.example.model.dto.request.AccountRequestDTO;
import org.example.model.dto.response.AccountResponseDTO;
import org.example.model.entity.Account;
import org.example.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;

  public AccountResponseDTO create(AccountRequestDTO dto) {
    return accountMapper.toDto(accountRepository.save(accountMapper.toEntity(dto)));
  }

  public List<AccountResponseDTO> readAll() {
    return accountMapper.toListDto(accountRepository.findAll());

  }

  public AccountResponseDTO readById(Long id) {
    Optional<Account> account = accountRepository.findById(id);
    return account.map(accountMapper::toDto).orElse(null);

  }
  public AccountResponseDTO update(Long id ,AccountRequestDTO accountRequestDTO) {
    if(accountRepository.existsById(id)) {
      Account account = accountMapper.toEntity(accountRequestDTO);
      account.setId(id);
      return accountMapper.toDto(accountRepository.save(account));
    }
    else{
      //exception
      return null;
    }
  }

  public HttpStatus delete(Long id) {
    var accountOptional = accountRepository.findById(id);
    if (accountOptional.isPresent()) {
      Account account = accountOptional.get();
      account.setIsDeleted(true);
    }
    return HttpStatus.I_AM_A_TEAPOT;
  }
}
