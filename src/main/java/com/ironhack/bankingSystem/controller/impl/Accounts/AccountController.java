package com.ironhack.bankingSystem.controller.impl.Accounts;

import com.ironhack.bankingSystem.controller.dto.AccountDTO;
import com.ironhack.bankingSystem.controller.dto.BalanceDTO;
import com.ironhack.bankingSystem.controller.dto.StatusDTO;
import com.ironhack.bankingSystem.controller.interfaces.Accounts.IAccountController;
import com.ironhack.bankingSystem.model.Account.Account;
import com.ironhack.bankingSystem.model.others.Money;
import com.ironhack.bankingSystem.repository.Accounts.AccountRepository;
import com.ironhack.bankingSystem.service.interfaces.Accounts.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController implements IAccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IAccountService accountService;

    @GetMapping("/accounts/by-username")
    public List<Account> findAccountsByAccountHolderId(@AuthenticationPrincipal UserDetails userDetails) {
        return accountService.getAllAccountsByUsername(userDetails.getUsername());
    }

    @PatchMapping("/updateBalance/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance (@PathVariable Integer id, @RequestBody BalanceDTO balance) {
        accountService.updateBalance(id, balance.getBalance());
    }

    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@RequestParam String hashedKey, @RequestBody AccountDTO accountDTO) {
        accountService.update(hashedKey, accountDTO);
    }

    @Override
    @PatchMapping("/updateStatus/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody @Valid StatusDTO statusDTO) {
        accountService.updateStatus(id, statusDTO.getStatus());
    }

}


