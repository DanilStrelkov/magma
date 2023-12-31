package org.example.service;

import lombok.AllArgsConstructor;
import org.example.mapper.CreditMapper;
import org.example.model.dto.request.CreditRequestDTO;
import org.example.model.dto.response.CreditResponseDTO;
import org.example.model.entity.Credit;
import org.example.repository.CreditRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreditService {
  private final CreditMapper creditMapper;
  private final CreditRepository creditRepository;

  public CreditResponseDTO create(CreditRequestDTO dto) {
    return creditMapper.toDto(creditRepository.save(creditMapper.toEntity(dto)));
  }

  public List<CreditResponseDTO> readAll() {
    return creditMapper.toListDto(creditRepository.findAll());
  }

  public CreditResponseDTO readById(Long id) {
    Optional<Credit> credit = creditRepository.findById(id);
    return credit.map(creditMapper::toDto).orElse(null);
  }
  public CreditResponseDTO update(Long id , CreditRequestDTO creditRequestDTO) {
    if(creditRepository.existsById(id)) {
      Credit credit = creditMapper.toEntity(creditRequestDTO);
      credit.setId(id);
      return creditMapper.toDto(creditRepository.save(credit));
    }
    else{
      //exception
      return null;
    }
  }

  public HttpStatus delete(Long id) {
    var creditOptional = creditRepository.findById(id);
    if (creditOptional.isPresent()) {
      Credit credit = creditOptional.get();
      credit.setIsDeleted(true);
    }
    return HttpStatus.I_AM_A_TEAPOT;
  }
}
