package org.example.service;

import lombok.AllArgsConstructor;
import org.example.mapper.CreditMapper;
import org.example.model.entity.Credit;
import org.example.model.enumerated.status.CreditStatus;
import org.example.repository.CreditRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreditService {
    private final CreditMapper creditMapper;
    private final CreditRepository creditRepository;

    public Credit create(Credit dto){
        return creditRepository.save(creditMapper.toEntity(dto));
    }
    public List<Credit> readAll(){
        return creditRepository.findAll();

    }
    public Optional<Credit> readById(Long id) {
        try {
            return creditRepository.findById(id);
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }
    public Credit update(Credit credit){
        return creditRepository.save(credit);
    }
    public Credit updateStatus(Long id, CreditStatus creditStatus){
        Optional<Credit> optionalCredit = creditRepository.findById(id);
        if (optionalCredit.isPresent()) {
            Credit credit = optionalCredit.get();
            credit.setCreditStatus(creditStatus);
            return creditRepository.save(credit);
        } else {
            throw new NoSuchElementException("Кредит не найден");
        }
    }

    public void delete(Long id){
        creditRepository.deleteById(id);
    }
}
