package org.example.model;


import lombok.Data;
import org.example.model.entity.*;


@Data
public class TransactionParticipant {
  private Account account;
  private Card card;
  private Credit credit;
  private CreditCard creditCard;
  private Deposit deposit;
  public Object Get(){
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
  public void Set(Object object){
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
