package com.ironhack.bankingSystem.service.impl.Accounts;

import com.ironhack.bankingSystem.controller.dto.CreditCardDTO;
import com.ironhack.bankingSystem.model.Account.CreditCard;
import com.ironhack.bankingSystem.repository.Accounts.CreditCardRepository;
import com.ironhack.bankingSystem.repository.Users.AccountHolderRepository;
import com.ironhack.bankingSystem.service.interfaces.Accounts.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreditCardService implements ICreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public CreditCard create (CreditCardDTO creditCardDTO){
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditLimit(creditCardDTO.getCreditLimit());
        creditCard.setInterestRate(creditCardDTO.getInterestRate());
        creditCard.setBalance(creditCardDTO.getBalance());
        creditCard.setDateOfCreation(new Date());
        creditCard.setPrimaryOwner(accountHolderRepository.findById(creditCardDTO.getPrimaryOwner()).get());
        //if(accountHolderRepository.findById(creditCardDTO.getSecondaryOwner()).isEmpty() ){
        if(creditCardDTO.getSecondaryOwner()==null){
        }else{
            creditCard.setSecondaryOwner(accountHolderRepository.findById(creditCardDTO.getSecondaryOwner()).get());
        }
        return creditCardRepository.save(creditCard);
    }

}
