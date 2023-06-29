package org.example.model.dto.request;


import lombok.Data;

@Data
public class ClientRequestDTO {
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String phone;
    private String email;
}
