package com.ironhack.bankingSystem.controller.impl.Accounts;

import com.ironhack.bankingSystem.controller.dto.CheckingDTO;
import com.ironhack.bankingSystem.controller.interfaces.Accounts.ICheckingController;
import com.ironhack.bankingSystem.model.Account.Checking;
import com.ironhack.bankingSystem.repository.Accounts.CheckingRepository;
import com.ironhack.bankingSystem.service.interfaces.Accounts.ICheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CheckingController implements ICheckingController {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    ICheckingService checkingService;

    @GetMapping("/checking")
    public List<Checking> findAllChecking() {
        return checkingRepository.findAll();
    }

    @GetMapping("/checking/by-id/{id}")
    public Optional<Checking> findById(@PathVariable Integer id) {
        return checkingRepository.findById(id);
    }

    @PostMapping("/create/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@RequestBody @Valid CheckingDTO checkingDTO) {
        return checkingService.create(checkingDTO);
    }
}
