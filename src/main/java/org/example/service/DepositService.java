package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.DepositRequestDTO;
import org.example.model.entity.Deposit;
import org.example.model.enumerated.status.DepositStatus;
import org.example.model.repository.DepositRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepositService {
    private final DepositRepository depositRepository;

    public Deposit create(DepositRequestDTO dto){
        return depositRepository.save(Deposit.builder()
                .moneyAmount(dto.getMoneyAmount())
                .secretWord(dto.getSecretWord())
                .depositLimit(dto.getDepositLimit())
                .client(dto.getClient())
                .build());
    }
    public List<Deposit> readAll(){
        return depositRepository.findAll();

    }
    public Optional<Deposit> readById(Long id){
        return depositRepository.findById(id);

    }
    public Deposit update(Deposit deposit){
        return depositRepository.save(deposit);
    }
    public Deposit updateStatus(Long id, DepositStatus depositStatus){
        Deposit deposit = depositRepository.findById(id).get();
        deposit.setDepositStatus(depositStatus);
        return depositRepository.save(deposit);
    }
    public void delete(Long id){
        depositRepository.deleteById(id);
    }
}
