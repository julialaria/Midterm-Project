package com.ironhack.bankingSystem.controller.interfaces.Accounts;

import com.ironhack.bankingSystem.controller.dto.AccountDTO;
import com.ironhack.bankingSystem.controller.dto.StatusDTO;
import com.ironhack.bankingSystem.model.others.Money;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface IAccountController {

    void updateStatus(Integer id, StatusDTO statusDTO);

    public void update (String hashedKey, AccountDTO accountDTO);

}
