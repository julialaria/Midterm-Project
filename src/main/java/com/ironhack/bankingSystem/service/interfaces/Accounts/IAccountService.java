package com.ironhack.bankingSystem.service.interfaces.Accounts;

import com.ironhack.bankingSystem.controller.dto.AccountDTO;
import com.ironhack.bankingSystem.controller.dto.StatusDTO;
import com.ironhack.bankingSystem.enums.Status;
import com.ironhack.bankingSystem.model.Account.Account;
import com.ironhack.bankingSystem.model.others.Money;

import java.util.List;

public interface IAccountService {
    public List<Account> getAllAccountsByUsername(String username);
    public void updateBalance(Integer id, Money balance);
    public void updateStatus(Integer id, Status status);
    public void update (String hashedKey, AccountDTO accountDTO);
}
