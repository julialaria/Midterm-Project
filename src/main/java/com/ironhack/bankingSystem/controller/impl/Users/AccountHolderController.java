package com.ironhack.bankingSystem.controller.impl.Users;


import com.ironhack.bankingSystem.controller.dto.AccountHolderDTO;
import com.ironhack.bankingSystem.controller.interfaces.Users.IAccountHolderController;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.Users.ThirdParty;
import com.ironhack.bankingSystem.repository.Users.AccountHolderRepository;
import com.ironhack.bankingSystem.service.interfaces.Users.IAccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountHolderController implements IAccountHolderController {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    IAccountHolderService accountHolderService;

    @GetMapping("/accountHolder")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAll() {
        return accountHolderService.getAll();
    }

    @GetMapping("/accountHolder/by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder findById(@PathVariable Integer id) {
        return accountHolderService.findById(id);
    }

    @PostMapping("/create/accountHolder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder store(@RequestBody @Valid AccountHolderDTO accountHolderDTO) {
        return accountHolderService.store(accountHolderDTO);
    }


}
