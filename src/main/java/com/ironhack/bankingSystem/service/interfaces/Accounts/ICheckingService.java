package com.ironhack.bankingSystem.service.interfaces.Accounts;

import com.ironhack.bankingSystem.controller.dto.CheckingDTO;
import com.ironhack.bankingSystem.model.Account.Account;
import com.ironhack.bankingSystem.model.Account.Checking;

public interface ICheckingService {

    public Object create(CheckingDTO checkingDTO);
}
