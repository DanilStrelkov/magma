package org.example.model.dto.request;

import lombok.Data;
import org.example.model.enumerated.type.ObjectType;

@Data
public class TransactionParticipantRequestDTO {
  private Long number;
  private ObjectType objectType;
}
