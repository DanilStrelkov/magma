package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.CardRequestDTO;
import org.example.model.entity.Account;
import org.example.model.entity.Card;
import org.example.repository.AccountRepository;
import org.example.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    public Card create(CardRequestDTO dto) {
        return cardRepository.save(Card.builder()
                .client(dto.getClient())
                .cardNumber(dto.getCardNumber())
                .expireDate(dto.getExpireDate())
                .cvv(dto.getCvv())
                .cardHolder(dto.getCardHolder())
                .type(dto.getType())
                .account(dto.getAccount())
                .status(dto.getStatus())
                .build());
    }

    public List<Card> readAll() {
        return cardRepository.findAll();

    }
    public String getCash(String cardNumber, String cvv, Long moneyAmount) {
        Optional<Card> cardOptional = cardRepository.getCardByCardNumber(cardNumber);
        if (cardOptional.isEmpty()) {
            return "Карта не найдена";
        }
        Card card = cardOptional.get();
        if(!card.getCvv().equals(cvv)) {
            return "Неверный CVV код";
        }
        if(moneyAmount <= 0) {
            return "Неверная сумма";
        }
        if( moneyAmount > card.getAccount().getMoneyAmount() || moneyAmount > card.getAccount().getDepositLimit()) {
            return "Недостаточно денег на счету или лимит снятия превышен";
        }
        Account account = card.getAccount();
        account.setMoneyAmount(card.getAccount().getMoneyAmount() - moneyAmount);
        account.setDepositLimit(card.getAccount().getDepositLimit() - moneyAmount);
        accountRepository.save(account);
        return "Успешное снятие! \n"
                + "Остаток на счету: "
                + account.getMoneyAmount() + "\n"
                + "Доступно к снятию в этом месяце: " + account.getDepositLimit();
    }
    public String putCash(String cardNumber, String cvv, Long moneyAmount) {
        Optional<Card> cardOptional = cardRepository.getCardByCardNumber(cardNumber);
        if (cardOptional.isEmpty()) {
            return "Карта не найдена";
        }
        Card card = cardOptional.get();
        if(!card.getCvv().equals(cvv)) {
            return "Неверный CVV код";
        }
        if(moneyAmount <= 0) {
            return "Неверная сумма";
        }
        Account account = card.getAccount();
        account.setMoneyAmount(card.getAccount().getMoneyAmount() + moneyAmount);
        accountRepository.save(account);
        return "Успешное пополнение! \n"
                + "Остаток на счету: "
                + account.getMoneyAmount();
    }

    public Optional<Card> readById(Long id) {
        return cardRepository.findById(id);

    }

    public Card update(Card card) {
        return cardRepository.save(card);
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }
}
