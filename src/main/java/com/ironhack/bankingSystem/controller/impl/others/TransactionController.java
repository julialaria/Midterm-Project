package com.ironhack.bankingSystem.controller.impl.others;

import com.ironhack.bankingSystem.controller.dto.CheckingDTO;
import com.ironhack.bankingSystem.controller.dto.TransactionDTO;
import com.ironhack.bankingSystem.controller.interfaces.others.ITransactionController;
import com.ironhack.bankingSystem.model.others.Transaction;
import com.ironhack.bankingSystem.repository.others.TransactionRepository;
import com.ironhack.bankingSystem.service.interfaces.others.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransactionController implements ITransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ITransactionService transactionService;

    @Override
    @GetMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAll() {
        return transactionService.getAll();
    }

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@RequestBody @Valid TransactionDTO transactionDTO, @AuthenticationPrincipal UserDetails userDetails) {
        return transactionService.create(transactionDTO, userDetails);
    }

}
