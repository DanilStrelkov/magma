package org.example.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.mapper.ClientMapper;
import org.example.model.dto.request.ClientRequestDTO;
import org.example.model.dto.response.ClientResponseDTO;
import org.example.model.entity.Client;
import org.example.model.enumerated.status.ClientStatus;
import org.example.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ClientService {

  private final ClientRepository clientRepository;
  private final ClientMapper clientMapper;
  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
          Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  public static final Pattern VALID_PHONE_REGEX = Pattern.compile("8" + "^\\d{10}$");

  public ClientResponseDTO create(ClientRequestDTO dto) {
    Client client = clientMapper.toEntity(dto);
    if(!validate(client.getEmail(), client.getPhone())) {
      // exception
    }
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

  public ClientResponseDTO update(Long id, ClientRequestDTO clientRequestDTO) {
    if (clientRepository.existsById(id)) {
      Client client = clientMapper.toEntity(clientRequestDTO);
      client.setId(id);
      return clientMapper.toDto(clientRepository.save(client));
    } else {
      //exception
      return null;
    }
  }

  public HttpStatus updateStatus(Long id, ClientStatus clientStatus) {
    Optional<Client> client = clientRepository.findById(id);
    if (client.isEmpty()) {
      return HttpStatus.I_AM_A_TEAPOT;
    }
    client.get().setStatus(clientStatus);
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

  public boolean validate(String email, String phone) {
    Matcher matcherEmail = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
    Matcher matcherPhone = VALID_PHONE_REGEX.matcher(phone);
    if (!matcherEmail.matches()) {
      System.out.println("Неверный формат email");
      return matcherEmail.matches();
    }
    if (!matcherPhone.matches()) {
      System.out.println("Неверный формат номера");
      return matcherPhone.matches();
    }
    return true;

  }
}
