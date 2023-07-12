package org.example.service;

import lombok.AllArgsConstructor;
import org.example.mapper.CreditCardMapper;
import org.example.model.dto.request.CreditCardRequestDTO;
import org.example.model.dto.response.CreditCardResponseDTO;
import org.example.model.entity.Client;
import org.example.model.entity.CreditCard;
import org.example.repository.CreditCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreditCardService {
  private final CreditCardRepository creditCardRepository;
  private final CreditCardMapper creditCardMapper;

  public CreditCardResponseDTO create(CreditCardRequestDTO creditCardRequestDTO) {
    return creditCardMapper.toDto(creditCardRepository.save(creditCardMapper.toEntity(creditCardRequestDTO)));
  }

  public List<CreditCardResponseDTO> readAll() {
    return creditCardMapper.toListDto(creditCardRepository.findAll());
  }

  public CreditCardResponseDTO readById(Long id) {
    Optional<CreditCard> creditCard = creditCardRepository.findById(id);
    return creditCard.map(creditCardMapper::toDto).orElse(null);
  }

  public CreditCardResponseDTO update(CreditCardRequestDTO creditRequestDTO) {
    creditCardRepository.save(creditCardMapper.toEntity(creditRequestDTO));
    return creditCardMapper.toDto(creditCardMapper.toEntity(creditRequestDTO));
  }

  public HttpStatus delete(Long id) {
    var creditCardOptional = creditCardRepository.findById(id);
    if (creditCardOptional.isPresent()) {
      CreditCard creditCard = creditCardOptional.get();
      creditCard.setIsDeleted(true);
    }
    return HttpStatus.I_AM_A_TEAPOT;
  }
}
