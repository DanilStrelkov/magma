package org.example.service;

import lombok.AllArgsConstructor;
import org.example.mapper.ClientMapper;
import org.example.model.dto.request.ClientRequestDTO;
import org.example.model.dto.response.ClientResponceDTO;
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

    public ClientResponceDTO create(ClientRequestDTO dto){
        return clientMapper.toDto(clientRepository.save(clientMapper.toEntity(dto)));
    }
    public List<ClientResponceDTO> readAll(){
        return clientMapper.toListDto(clientRepository.findAll());

    }
    public ClientResponceDTO readById(Long id){
        Optional<Client> client = clientRepository.findById(id);
        return client.map(clientMapper::toDto).orElse(null);

    }
    public ClientResponceDTO update(ClientRequestDTO clientRequestDTO){
        clientRepository.save(clientMapper.toEntity(clientRequestDTO));
        return clientMapper.toDto(clientMapper.toEntity(clientRequestDTO));
    }
    public HttpStatus updateStatus(Long id, ClientStatus clientStatus){
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            return HttpStatus.I_AM_A_TEAPOT;
        }
        client.get().setClientStatus(clientStatus);
        clientRepository.save(client.get());
        return HttpStatus.OK;
    }
    public HttpStatus delete(Long id){
        clientRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
