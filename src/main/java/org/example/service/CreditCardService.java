package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.entity.Account;
import org.example.model.entity.CreditCard;
import org.example.repository.AccountRepository;
import org.example.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final AccountRepository accountRepository;

    public CreditCard create(CreditCard dto) {
        return creditCardRepository.save(CreditCard.builder()
                .client(dto.getClient())
                .cardNumber(String.valueOf(dto.getCardNumber()))
                .expireDate(dto.getExpireDate())
                .cvv(String.valueOf(dto.getCvv()))
                .cardHolder(dto.getCardHolder())
                .type(dto.getType())
                .account(dto.getAccount())
                .status(dto.getStatus())
                .build());
    }

    public List<CreditCard> readAll() {
        return creditCardRepository.findAll();

    }
    public String getCash(String cardNumber, String cvv, Long moneyAmount) {
        Optional<CreditCard> creditCardOptional = creditCardRepository.getCardByCardNumber(cardNumber);
        if (creditCardOptional.isEmpty()) return "Карта не найдена";
        CreditCard creditCard = creditCardOptional.get();
        if(!creditCard.getCvv().equals(cvv)) return "Неверный CVV код";
        if(moneyAmount <= 0) return "Неверная сумма";
        if(moneyAmount > creditCard.getAccount().getMoneyAmount() || moneyAmount > creditCard.getAccount().getDepositLimit())
            return "Недостаточно денег на счету или лимит снятия превышен";
        Account account = creditCard.getAccount();
        account.setMoneyAmount(creditCard.getAccount().getMoneyAmount() - moneyAmount);
        account.setDepositLimit(creditCard.getAccount().getDepositLimit() - moneyAmount);
        accountRepository.save(account);
        return "Успешное снятие! \n"
                + "Остаток на счету: "
                + account.getMoneyAmount() + "\n"
                + "Доступно к снятию в этом месяце: " + account.getDepositLimit();
    }
    public String putCash(String cardNumber, String cvv, Long moneyAmount) {
        Optional<CreditCard> creditCardOptional = creditCardRepository.getCardByCardNumber(cardNumber);
        if (creditCardOptional.isEmpty()) return "Карта не найдена";
        CreditCard creditCard = creditCardOptional.get();
        if(!creditCard.getCvv().equals(cvv)) return "Неверный CVV код";
        if(moneyAmount <= 0) return "Неверная сумма";
        Account account = creditCard.getAccount();
        account.setMoneyAmount(creditCard.getAccount().getMoneyAmount() + moneyAmount);
        accountRepository.save(account);
        return "Успешное пополнение! \n"
                + "Остаток на счету: "
                + account.getMoneyAmount();
    }

    public Optional<CreditCard> readById(Long id) {
        return creditCardRepository.findById(id);

    }

    public CreditCard update(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public void delete(Long id) {
        creditCardRepository.deleteById(id);
    }
}
