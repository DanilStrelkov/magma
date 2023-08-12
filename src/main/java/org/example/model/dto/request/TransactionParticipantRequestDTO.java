package org.example.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.model.enumerated.type.ObjectType;

@Data
public class TransactionParticipantRequestDTO {
  private String number;
  private ObjectType objectType;
}
