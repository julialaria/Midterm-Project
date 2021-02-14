package com.ironhack.bankingSystem.controller.interfaces.Accounts;

import com.ironhack.bankingSystem.controller.dto.SavingsDTO;
import com.ironhack.bankingSystem.model.Account.Savings;

import java.util.Optional;

public interface ISavingsController {

    public Optional<Savings> findById(Integer id);
    public Savings create (SavingsDTO savingsDTO);
    /*Savings store(Savings savings);*/
}
