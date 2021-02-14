package com.ironhack.bankingSystem.controller.impl.Accounts;

import com.ironhack.bankingSystem.controller.dto.SavingsDTO;
import com.ironhack.bankingSystem.controller.interfaces.Accounts.ISavingsController;
import com.ironhack.bankingSystem.model.Account.Savings;
import com.ironhack.bankingSystem.repository.Accounts.SavingsRepository;
import com.ironhack.bankingSystem.service.interfaces.Accounts.ISavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class SavingsController implements ISavingsController {

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    ISavingsService savingsService;

    @GetMapping("/savings")
    public List<Savings> findAllSavings() {
        return savingsRepository.findAll();
    }

    @GetMapping("/savings/by-id/{id}")
    public Optional<Savings> findById(@PathVariable Integer id) {
        return savingsRepository.findById(id);
    }

    @PostMapping("/create/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings create(@RequestBody @Valid SavingsDTO savingsDTO) {
        return savingsService.create(savingsDTO);
    }

}
