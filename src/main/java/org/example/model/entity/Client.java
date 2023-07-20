package org.example.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.model.enumerated.Role;
import org.example.model.enumerated.status.ClientStatus;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
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
  @Column(name = "reg_date")
  @NotNull
  @Builder.Default
  private LocalDateTime regDate = LocalDateTime.now();
  @Column(name = "email")
  private String email;
  @Builder.Default
  @NotNull
  @Column(name = "client_status")
  @Enumerated(EnumType.STRING)
  private ClientStatus status = ClientStatus.ACTIVE;
  @Builder.Default
  @NotNull
  @Column(name = "client_role")
  @Enumerated(EnumType.STRING)
  private Role roles = Role.USER;
  @Column(name = "password")
  private String password;
  @Column(name = "login")
  private String login;

  @Column(name = "deleted")
  @NotNull
  @Builder.Default
  private Boolean isDeleted = false;
  @Column(name = "number")
  @NotNull
  private String number;
}
