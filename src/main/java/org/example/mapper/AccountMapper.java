package org.example.mapper;

import org.example.model.dto.request.AccountRequestDTO;
import org.example.model.dto.response.AccountResponseDTO;
import org.example.model.entity.Account;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface AccountMapper {
    Account toEntity(AccountRequestDTO dto);
    AccountResponseDTO toDto(Account account);

    List<AccountResponseDTO> toListDto(List<Account> accounts);
}
