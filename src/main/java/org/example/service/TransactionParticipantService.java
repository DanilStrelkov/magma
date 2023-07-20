package org.example.service;

import lombok.AllArgsConstructor;
import org.example.mapper.TransactionParticipantMapper;
import org.example.model.TransactionParticipant;
import org.example.model.dto.request.TransactionParticipantRequestDTO;
import org.example.model.entity.*;
import org.example.repository.*;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionParticipantService {
  private final CardRepository cardRepository;
  private final AccountRepository accountRepository;
  private final DepositRepository depositRepository;
  private final CreditCardRepository creditCardRepository;
  private final CreditRepository creditRepository;
  private final TransactionParticipantMapper transactionParticipantMapper;

  public TransactionParticipant getMoneyAmount(TransactionParticipantRequestDTO transactionParticipantRequestDTO) {
    TransactionParticipant transactionParticipant = transactionParticipantMapper.toEntity(transactionParticipantRequestDTO);
    switch (transactionParticipant.getObjectType()) {
      case DEPOSIT -> {
        transactionParticipant.setMoneyAmount(depositRepository.getDepositByNumber(transactionParticipant.getNumber()).get().getMoneyAmount());
        return transactionParticipant;
      }
      case CARD -> {
        transactionParticipant.setMoneyAmount(cardRepository.getCardByNumber(transactionParticipant.getNumber()).get().getMoneyAmount());
        return transactionParticipant;
      }
      case CREDIT_CARD -> {
        transactionParticipant.setMoneyAmount(creditCardRepository.getCreditCardByNumber(transactionParticipant.getNumber()).get().getMoneyAmount());
        return transactionParticipant;
      }
      case ACCOUNT -> {
        transactionParticipant.setMoneyAmount(accountRepository.getAccountByNumber(transactionParticipant.getNumber()).get().getMoneyAmount());
        return transactionParticipant;
      }
      case CREDIT -> {
        transactionParticipant.setMoneyAmount(creditRepository.getCreditByNumber(transactionParticipant.getNumber()).get().getMoneyAmount());
        return transactionParticipant;
      }
      default -> {
        return null;
      }
    }
  }
  public void update(TransactionParticipant transactionParticipant){
    switch (transactionParticipant.getObjectType()) {
      case DEPOSIT -> {
        Deposit deposit = depositRepository.getDepositByNumber(transactionParticipant.getNumber()).get();
        deposit.setMoneyAmount(transactionParticipant.getMoneyAmount());
        depositRepository.save(deposit);
      }
      case CARD -> {
        transactionParticipant.setMoneyAmount(cardRepository.getCardByNumber(transactionParticipant.getNumber()).get().getMoneyAmount());
        Card card = cardRepository.getCardByNumber(transactionParticipant.getNumber()).get();
        card.setMoneyAmount(transactionParticipant.getMoneyAmount());
        cardRepository.save(card);
      }
      case CREDIT_CARD -> {
        transactionParticipant.setMoneyAmount(creditCardRepository.getCreditCardByNumber(transactionParticipant.getNumber()).get().getMoneyAmount());
        CreditCard creditCard = creditCardRepository.getCardByCardNumber(transactionParticipant.getNumber()).get();
        creditCard.setMoneyAmount(transactionParticipant.getMoneyAmount());
        creditCardRepository.save(creditCard);
      }
      case ACCOUNT -> {
        transactionParticipant.setMoneyAmount(accountRepository.getAccountByNumber(transactionParticipant.getNumber()).get().getMoneyAmount());
        Account account = accountRepository.getAccountByNumber(transactionParticipant.getNumber()).get();
        account.setMoneyAmount(transactionParticipant.getMoneyAmount());
        accountRepository.save(account);
      }
      case CREDIT -> {
        transactionParticipant.setMoneyAmount(creditRepository.getCreditByNumber(transactionParticipant.getNumber()).get().getMoneyAmount());
        Credit credit = creditRepository.getCreditByNumber(transactionParticipant.getNumber()).get();
        credit.setMoneyAmount(transactionParticipant.getMoneyAmount());
        creditRepository.save(credit);
      }
      default -> {
        //exception
      }
    }

  }


}
