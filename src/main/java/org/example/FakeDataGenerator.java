package org.example;

import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.model.entity.*;
import org.example.model.enumerated.status.*;
import org.example.model.enumerated.term.TermLength;
import org.example.model.enumerated.type.CardType;
import org.example.model.enumerated.type.CreditType;
import org.example.model.enumerated.type.CurrencyType;
import org.example.model.enumerated.type.DepositType;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "test-data", name = "generate", havingValue = "true")
public class FakeDataGenerator implements CommandLineRunner {

  private final ClientRepository clientRepository;
  private final AccountRepository accountRepository;
  private final DepositRepository depositRepository;
  private final CardRepository cardRepository;
  private final CreditCardRepository creditCardRepository;
  private final CreditRepository creditRepository;

  @Value("${test-data.client}")
  private Integer clientCount;
  @Value("${test-data.account}")
  private Integer accountCount;
  @Value("${test-data.deposit}")
  private Integer depositCount;
  @Value("${test-data.card}")
  private Integer cardCount;
  @Value("${test-data.credit}")
  private Integer creditCount;
  @Value("${test-data.creditCard}")
  private Integer creditCardCount;

  @Override
  @Transactional
  public void run(String... args) throws Exception {


    Faker fakerEn = new Faker(new Locale("en"));
    Faker fakerRu = new Faker(new Locale("ru"));

    List<Client> clients = new ArrayList<>();
    List<Account> accounts = new ArrayList<>();
    List<Deposit> deposits = new ArrayList<>();
    List<Card> cards = new ArrayList<>();
    List<Credit> credits = new ArrayList<>();
    List<CreditCard> creditCards = new ArrayList<>();
    Client client;
    Account account;
    Deposit deposit;
    Card card;
    Credit credit;
    CreditCard creditCard;

    for (int i = 0; i < clientCount; i++) {
      client = Client.builder()
          .firstName(fakerRu.name().firstName())
          .middleName(fakerRu.name().nameWithMiddle())
          .lastName(fakerRu.name().lastName())
          .address(fakerRu.address().fullAddress())
          .regDate(LocalDateTime.now())
          .email(fakerEn.internet().emailAddress())
          .phone(fakerRu.phoneNumber().phoneNumber())
          .status(ClientStatus.ACTIVE)
          .number(fakerRu.random().nextInt(100000,999999).toString())
          .build();
      client.setIsDeleted(false);
      client.setLogin(client.getEmail());
      client.setPassword(client.getEmail());
      clients.add(client);
    }

    clientRepository.saveAll(clients);

    for (int i = 0; i < accountCount; i ++) {
      Client owner = clients.get(fakerRu.random().nextInt(0, clients.size() - 1));
      account = Account.builder()
          .creationDate(LocalDateTime.now())
          .client(owner)
          .moneyAmount(fakerRu.random().nextInt(20000,500000))
          .depositLimit(fakerRu.random().nextInt(10000,50000))
          .secretWord(owner.getLastName())
          .currencyType(CurrencyType.RUBLE)
          .number(fakerRu.random().nextInt(100000,999999).toString())
          .build();
      account.setIsDeleted(false);
      account.setStatus(AccountStatus.OPEN);
      accounts.add(account);
    }

    accountRepository.saveAll(accounts);

    for (int i = 0; i < cardCount; i ++) {
      Client owner = clients.get(fakerRu.random().nextInt(0, clients.size() - 1));
      Account accountOwner = accounts.get(fakerRu.random().nextInt(0, accounts.size() - 1));
      while (accountOwner.getClient().equals(owner)) {
        owner = clients.get(fakerRu.random().nextInt(0, clients.size() - 1));
      }
      card = Card.builder()
          .expireDate(LocalDateTime.now().plusYears(10))
          .cardNumber(("40005000" + fakerRu.random().nextInt(10000000,99999999).toString()))
          .status(CardStatus.ACTIVE)
          .cardHolder(owner.getFirstName() + " " + owner.getLastName())
          .moneyAmount(fakerRu.random().nextInt(20000,500000))
          .cvv(fakerRu.random().nextInt(100,999).toString())
          .account(accountOwner)
          .client(owner)
          .type(CardType.PHYSICAL)
          .number(fakerRu.random().nextInt(100000,999999).toString())
          .build();
      card.setIsDeleted(false);
      cards.add(card);
    }
    cardRepository.saveAll(cards);

    for (int i = 0; i < depositCount; i ++) {
      Client owner = clients.get(fakerRu.random().nextInt(0, clients.size() - 1));
      deposit = Deposit.builder()
          .creationDate(LocalDateTime.now())
          .currencyType(CurrencyType.RUBLE)
          .moneyAmount(fakerRu.random().nextInt(20000,500000))
          .status(DepositStatus.OPEN)
          .type(DepositType.REPLENISHABLE)
          .depositLimit(fakerRu.random().nextInt(10000,50000))
          .income(fakerRu.random().nextInt(2,5).doubleValue())
          .incomeDate(LocalDateTime.now().plusMonths(1))
          .client(owner)
          .expireDate(LocalDateTime.now().plusYears(2))
          .number(fakerRu.random().nextInt(100000,999999).toString())
          .build();
      deposit.setIsDeleted(false);
      deposits.add(deposit);
    }

    depositRepository.saveAll(deposits);

    for (int i = 0; i < creditCount; i ++) {
      Client owner = clients.get(fakerRu.random().nextInt(0, clients.size() - 1));
      credit = Credit.builder()
          .creationDate(LocalDateTime.now())
          .creditStatus(CreditStatus.OPEN)
          .moneyAmount(fakerRu.random().nextInt(20000,500000))
          .percent(fakerRu.random().nextInt(2,5).doubleValue())
          .currencyType(CurrencyType.RUBLE)
          .term(TermLength.TWO_YEARS)
          .type(CreditType.TARGET)
          .number(fakerRu.random().nextInt(100000,999999).toString())
          .client(owner)
          .build();
      credit.setIsDeleted(false);
      credits.add(credit);
    }
    creditRepository.saveAll(credits);

    for (int i = 0; i < creditCardCount; i ++) {
      Client owner = clients.get(fakerRu.random().nextInt(0, clients.size() - 1));
      creditCard = CreditCard.builder()
          .expireDate(LocalDateTime.now().plusYears(10))
          .cardNumber(("40005000" + fakerRu.random().nextInt(10000000,99999999).toString()))
          .status(CardStatus.ACTIVE)
          .cardHolder(owner.getFirstName() + " " + owner.getLastName())
          .moneyAmount(fakerRu.random().nextInt(20000,500000))
          .cvv(fakerRu.random().nextInt(100,999).toString())
          .percent(fakerRu.random().nextInt(2,5).doubleValue())
          .type(CardType.PHYSICAL)
          .client(owner)
          .number(fakerRu.random().nextInt(100000,999999).toString())
          .build();
      creditCard.setIsDeleted(false);
      creditCards.add(creditCard);
    }

    creditCardRepository.saveAll(creditCards);
  }
}
