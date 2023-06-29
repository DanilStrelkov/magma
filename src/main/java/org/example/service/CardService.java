package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.CardRequestDTO;
import org.example.model.entity.Card;
import org.example.model.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

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
