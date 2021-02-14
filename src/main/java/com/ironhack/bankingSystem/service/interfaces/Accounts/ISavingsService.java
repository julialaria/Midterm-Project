package com.ironhack.bankingSystem.service.interfaces.Accounts;

import com.ironhack.bankingSystem.controller.dto.SavingsDTO;
import com.ironhack.bankingSystem.model.Account.Savings;

public interface ISavingsService {

    public Savings create(SavingsDTO savingsDTO);
    /*Savings store(Savings savings);*/

}
