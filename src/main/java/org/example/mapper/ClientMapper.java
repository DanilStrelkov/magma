package org.example.mapper;

import org.example.model.dto.request.ClientRequestDTO;
import org.example.model.dto.response.ClientResponceDTO;
import org.example.model.entity.Client;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ClientMapper {

    Client toEntity(ClientRequestDTO dto);
    ClientResponceDTO toDto(Client client);

    List<ClientResponceDTO> toListDto(List<Client> clients);
}
