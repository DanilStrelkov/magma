package org.example.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.mapper.ClientMapper;
import org.example.model.dto.request.ClientRequestDTO;
import org.example.model.dto.response.ClientResponseDTO;
import org.example.model.entity.Card;
import org.example.model.entity.Client;
import org.example.model.enumerated.status.ClientStatus;
import org.example.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

  private final ClientRepository clientRepository;
  private final ClientMapper clientMapper;

  public ClientResponseDTO create(ClientRequestDTO dto) {
    return clientMapper.toDto(clientRepository.save(clientMapper.toEntity(dto)));
  }

  public List<ClientResponseDTO> readAll() {
    return clientMapper.toListDto(clientRepository.findAll());

  }

  public ClientResponseDTO readById(Long id) {
    Optional<Client> client = clientRepository.findById(id);
    return client.map(clientMapper::toDto).orElse(null);
  }

  public Optional<Client> getByLogin(@NonNull String login) {
    return clientRepository.findAll().stream()
            .filter(user -> login.equals(user.getLogin()))
            .findFirst();
  }
  public ClientResponseDTO update(ClientRequestDTO clientRequestDTO) {
    clientRepository.save(clientMapper.toEntity(clientRequestDTO));
    return clientMapper.toDto(clientMapper.toEntity(clientRequestDTO));
  }

  public HttpStatus updateStatus(Long id, ClientStatus clientStatus) {
    Optional<Client> client = clientRepository.findById(id);
    if (client.isEmpty()) {
      return HttpStatus.I_AM_A_TEAPOT;
    }
    client.get().setClientStatus(clientStatus);
    clientRepository.save(client.get());
    return HttpStatus.OK;
  }

  public HttpStatus delete(Long id) {
    var clientOptional = clientRepository.findById(id);
    if (clientOptional.isPresent()) {
      Client client = clientOptional.get();
      client.setIsDeleted(true);
    }
    return HttpStatus.I_AM_A_TEAPOT;
  }
}
