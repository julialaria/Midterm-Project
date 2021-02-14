package com.ironhack.bankingSystem.controller.impl.Accounts;

import com.ironhack.bankingSystem.controller.dto.CreditCardDTO;
import com.ironhack.bankingSystem.controller.interfaces.Accounts.ICreditCardController;
import com.ironhack.bankingSystem.model.Account.CreditCard;
import com.ironhack.bankingSystem.repository.Accounts.CreditCardRepository;
import com.ironhack.bankingSystem.service.interfaces.Accounts.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CreditCardController implements ICreditCardController {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    ICreditCardService creditCardService;

    @GetMapping("/creditCard")
    public List<CreditCard> findAllCreditCard() {
        return creditCardRepository.findAll();
    }

    @GetMapping("/creditCard/by-id/{id}")
    public Optional<CreditCard> findById(@PathVariable Integer id) {
        return creditCardRepository.findById(id);
    }


    @PostMapping("/create/creditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard create(@RequestBody @Valid CreditCardDTO creditcardDTO) {
        return creditCardService.create(creditcardDTO);
    }

}
