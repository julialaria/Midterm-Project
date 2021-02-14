package com.ironhack.bankingSystem.service.interfaces.Accounts;

import com.ironhack.bankingSystem.controller.dto.CreditCardDTO;
import com.ironhack.bankingSystem.controller.dto.SavingsDTO;
import com.ironhack.bankingSystem.model.Account.CreditCard;
import com.ironhack.bankingSystem.model.Account.Savings;

public interface ICreditCardService {

    /*CreditCard store (CreditCard creditCard);*/
    public CreditCard create(CreditCardDTO creditCardDTO);
}
