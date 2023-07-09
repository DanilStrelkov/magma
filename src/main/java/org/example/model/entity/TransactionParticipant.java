package org.example.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_participant")
public class TransactionParticipant {
  @Id
  private Long id;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "account", referencedColumnName = "id")
  private Account account;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "card", referencedColumnName = "id")
  private Card card;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "credit", referencedColumnName = "id")
  private Credit credit;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "credit_card", referencedColumnName = "id")
  private CreditCard creditCard;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "deposit", referencedColumnName = "id")
  private Deposit deposit;


  public Object get(){
    if(account != null){
      return account;
    }
    else if(card != null){
      return card;
    }
    else if(creditCard != null){
      return creditCard;
    }
    else if(credit != null){
      return credit;
    }
    else if(deposit != null){
      return deposit;
    }
    else{
      return null;
      //exeption
    }
  }
  public void set(Object object){
    if(account == null && card == null && creditCard == null && credit == null && deposit == null ){
      switch (object.getClass().getName()) {
        case "Account" -> account = (Account) object;
        case "Card" -> card = (Card) object;
        case "CreditCard" -> creditCard = (CreditCard) object;
        case "Credit" -> credit = (Credit) object;
        case "Deposit" -> deposit = (Deposit) object;
        default -> {
          //exeption
        }


      }
    }
  }

}
