package com.ironhack.bankingSystem.controller.interfaces.Users;

import com.ironhack.bankingSystem.controller.dto.AccountHolderDTO;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.Users.ThirdParty;

import java.util.List;

public interface IAccountHolderController {

    List<AccountHolder> getAll();

    public AccountHolder findById(Integer id);

    AccountHolder store(AccountHolderDTO accountHolderDTO);
}
