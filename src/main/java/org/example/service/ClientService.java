package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.ClientDTO;
import org.example.model.entity.Client;
import org.example.model.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client create(ClientDTO dto){
        return clientRepository.save(Client.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .middleName(dto.getMiddleName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build());
    }
    public List<Client> readAll(){
        return clientRepository.findAll();

    }
    public Optional<Client> readById(Long id){
        return clientRepository.findById(id);

    }
    public Client update(Client client){
        return clientRepository.save(client);
    }
    public void delete(Long id){
        clientRepository.deleteById(id);
    }
}
