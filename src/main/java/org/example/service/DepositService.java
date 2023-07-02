package org.example.service;

import lombok.AllArgsConstructor;
import org.example.mapper.DepositMapper;
import org.example.model.dto.request.DepositRequestDTO;
import org.example.model.dto.response.DepositResponceDTO;
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

    public DepositResponceDTO create(DepositRequestDTO dto){
        return depositMapper.toDto(depositRepository.save(depositMapper.toEntity(dto)));
    }
    public List<DepositResponceDTO> readAll(){
        return depositMapper.toListDto(depositRepository.findAll());

    }
    public DepositResponceDTO readById(Long id){
        Optional<Deposit> deposit = depositRepository.findById(id);
        return deposit.map(depositMapper::toDto).orElse(null);

    }
    public DepositResponceDTO update(DepositRequestDTO depositRequestDTO){
        return depositMapper.toDto(depositRepository.save(depositMapper.toEntity(depositRequestDTO)));
    }
    public HttpStatus updateStatus(Long id, DepositStatus depositStatus){
        Optional<Deposit> deposit = depositRepository.findById(id);
        if(deposit.isEmpty()){
            return HttpStatus.I_AM_A_TEAPOT;
        }
        deposit.get().setDepositStatus(depositStatus);
        depositRepository.save(deposit.get());
        return HttpStatus.OK;
    }
    public HttpStatus delete(Long id){
        depositRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
