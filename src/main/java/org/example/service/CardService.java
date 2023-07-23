package org.example.service;

import lombok.AllArgsConstructor;
import org.example.mapper.CardMapper;
import org.example.model.dto.request.CardRequestDTO;
import org.example.model.dto.response.CardResponseDTO;
import org.example.model.entity.Account;
import org.example.model.entity.Card;
import org.example.repository.AccountRepository;
import org.example.repository.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardService {
  private final CardRepository cardRepository;
  private final AccountRepository accountRepository;
  private final CardMapper cardMapper;

  public CardResponseDTO create(CardRequestDTO cardRequestDTO) {
    return cardMapper.toDto(cardRepository.save(cardMapper.toEntity(cardRequestDTO)));
  }

  public List<CardResponseDTO> readAll() {
    return cardMapper.toListDto(cardRepository.findAll());

  }

  public CardResponseDTO readById(Long id) {
    Optional<Card> card = cardRepository.findById(id);
    return card.map(cardMapper::toDto).orElse(null);

  }
  public CardResponseDTO update(Long id , CardRequestDTO cardRequestDTO) {
    if(cardRepository.existsById(id)) {
      Card card = cardMapper.toEntity(cardRequestDTO);
      card.setId(id);
      return cardMapper.toDto(cardRepository.save(card));
    }
    else{
      //exception
      return null;
    }
  }

  public HttpStatus delete(Long id) {
    var cardOptional = cardRepository.findById(id);
    if (cardOptional.isPresent()) {
      Card card = cardOptional.get();
      card.setIsDeleted(true);
    }
      return HttpStatus.I_AM_A_TEAPOT;
    }

  public String getCash(String cardNumber, String cvv, int moneyAmount) {
    Optional<Card> cardOptional = cardRepository.getCardByCardNumber(cardNumber);
    if (cardOptional.isEmpty()) {
      return "Карта не найдена";
    }
    Card card = cardOptional.get();
    if (!card.getCvv().equals(cvv)) {
      return "Неверный CVV код";
    }
    if (moneyAmount <= 0) {
      return "Неверная сумма";
    }
    if (moneyAmount > card.getAccount().getMoneyAmount() || moneyAmount > card.getAccount().getDepositLimit()) {
      return "Недостаточно денег на счету или лимит снятия превышен";
    }
    Account account = card.getAccount();
    account.setMoneyAmount((int) (card.getAccount().getMoneyAmount() - moneyAmount));
    account.setDepositLimit(card.getAccount().getDepositLimit() - moneyAmount);
    accountRepository.save(account);
    return "Успешное снятие! \n"
            + "Остаток на счету: "
            + account.getMoneyAmount() + "\n"
            + "Доступно к снятию в этом месяце: " + account.getDepositLimit();
  }

  public String putCash(String cardNumber, String cvv, int moneyAmount) {
    Optional<Card> cardOptional = cardRepository.getCardByCardNumber(cardNumber);
    if (cardOptional.isEmpty()) {
      return "Карта не найдена";
    }
    Card card = cardOptional.get();
    if (!card.getCvv().equals(cvv)) {
      return "Неверный CVV код";
    }
    if (moneyAmount <= 0) {
      return "Неверная сумма";
    }
    Account account = card.getAccount();
    account.setMoneyAmount((int) (card.getAccount().getMoneyAmount() + moneyAmount));
    accountRepository.save(account);
    return "Успешное пополнение! \n"
            + "Остаток на счету: "
            + account.getMoneyAmount();
  }

}
