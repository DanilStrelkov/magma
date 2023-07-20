package org.example.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enumerated.type.ObjectType;



@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "xuy")
public class TransactionParticipant {
  private String number;
  private ObjectType objectType;
  private int moneyAmount;
  @Id
  private Long id;
}
