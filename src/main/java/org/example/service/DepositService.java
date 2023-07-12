package org.example.service;

import lombok.AllArgsConstructor;
import org.example.mapper.DepositMapper;
import org.example.model.dto.request.DepositRequestDTO;
import org.example.model.dto.response.DepositResponseDTO;
import org.example.model.entity.Credit;
import org.example.model.entity.Deposit;
import org.example.model.enumerated.status.DepositStatus;
import org.example.repository.DepositRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepositService {
  private final DepositMapper depositMapper;
  private final DepositRepository depositRepository;

  public DepositResponseDTO create(DepositRequestDTO dto) {
    return depositMapper.toDto(depositRepository.save(depositMapper.toEntity(dto)));
  }

  public List<DepositResponseDTO> readAll() {
    return depositMapper.toListDto(depositRepository.findAll());

  }

  public DepositResponseDTO readById(Long id) {
    Optional<Deposit> deposit = depositRepository.findById(id);
    return deposit.map(depositMapper::toDto).orElse(null);

  }
  public DepositResponseDTO update(Long id , DepositRequestDTO depositRequestDTO) {
    if(depositRepository.existsById(id)) {
      Deposit deposit = depositMapper.toEntity(depositRequestDTO);
      deposit.setId(id);
      return depositMapper.toDto(depositRepository.save(deposit));
    }
    else{
      //exception
      return null;
    }
  }

  public HttpStatus updateStatus(Long id, DepositStatus depositStatus) {
    Optional<Deposit> deposit = depositRepository.findById(id);
    if (deposit.isEmpty()) {
      return HttpStatus.I_AM_A_TEAPOT;
    }
    deposit.get().setDepositStatus(depositStatus);
    depositRepository.save(deposit.get());
    return HttpStatus.OK;
  }

  public HttpStatus delete(Long id) {
    var depositOptional = depositRepository.findById(id);
    if (depositOptional.isPresent()) {
      Deposit deposit = depositOptional.get();
      deposit.setIsDeleted(true);
    }
    return HttpStatus.I_AM_A_TEAPOT;
  }
}
