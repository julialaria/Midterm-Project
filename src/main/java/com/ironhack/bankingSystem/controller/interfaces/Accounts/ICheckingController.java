package com.ironhack.bankingSystem.controller.interfaces.Accounts;

import com.ironhack.bankingSystem.controller.dto.CheckingDTO;
import com.ironhack.bankingSystem.model.Account.Account;
import com.ironhack.bankingSystem.model.Account.Checking;
import com.ironhack.bankingSystem.model.Account.Savings;

import java.util.Optional;

public interface ICheckingController {
    public Optional<Checking> findById(Integer id);
    public Object create (CheckingDTO checkingDTO);
}
