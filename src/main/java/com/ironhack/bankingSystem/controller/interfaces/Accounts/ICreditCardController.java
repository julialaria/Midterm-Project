package com.ironhack.bankingSystem.controller.interfaces.Accounts;

import com.ironhack.bankingSystem.controller.dto.CreditCardDTO;
import com.ironhack.bankingSystem.controller.dto.SavingsDTO;
import com.ironhack.bankingSystem.model.Account.CreditCard;
import com.ironhack.bankingSystem.model.Account.Savings;

import java.util.Optional;

public interface ICreditCardController {

    public Optional<CreditCard> findById(Integer id);

    /*CreditCard store (CreditCard creditCard);*/
    public CreditCard create (CreditCardDTO creditCardDTO);
}
