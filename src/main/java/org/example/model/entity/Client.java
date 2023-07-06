package org.example.model.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.model.enumerated.Role;
import org.example.model.enumerated.status.ClientStatus;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Builder.Default
    @NotNull
    @Column(name = "client_status")
    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus = ClientStatus.ACTIVE;
    @NotNull
    @Builder.Default
    @Column(name = "client_role")
    @Enumerated(EnumType.STRING)
    private Role roles = Role.USER;
    @Column(name = "password")
    private String password;
}
