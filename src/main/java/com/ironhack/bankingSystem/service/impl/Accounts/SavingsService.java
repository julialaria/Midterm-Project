package com.ironhack.bankingSystem.service.impl.Accounts;

import com.ironhack.bankingSystem.controller.dto.SavingsDTO;
import com.ironhack.bankingSystem.enums.Status;
import com.ironhack.bankingSystem.model.Account.Savings;
import com.ironhack.bankingSystem.repository.Accounts.SavingsRepository;
import com.ironhack.bankingSystem.repository.Users.AccountHolderRepository;
import com.ironhack.bankingSystem.service.interfaces.Accounts.ISavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SavingsService implements ISavingsService {

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

        public Savings create (SavingsDTO savingsDTO){
            Savings savings = new Savings();
            savings.setSecretKey(savingsDTO.getSecretKey());
            savings.setMinimumBalance(savingsDTO.getMinimumBalance());
            savings.setInterestRate(savingsDTO.getInterestRate());
            savings.setStatus(Status.ACTIVE);
            savings.setBalance(savingsDTO.getBalance());
            savings.setDateOfCreation(new Date());
            savings.setPrimaryOwner(accountHolderRepository.findById(savingsDTO.getPrimaryOwner()).get());
           // if(accountHolderRepository.findById(savingsDTO.getSecondaryOwner()).isEmpty() ){
            if(savingsDTO.getSecondaryOwner()==null){
            }else{
                savings.setSecondaryOwner(accountHolderRepository.findById(savingsDTO.getSecondaryOwner()).get());
            }
            return savingsRepository.save(savings);
        }

    }
