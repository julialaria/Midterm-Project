package com.ironhack.bankingSystem.service.impl.Accounts;

import com.ironhack.bankingSystem.controller.dto.CheckingDTO;
import com.ironhack.bankingSystem.enums.Status;
import com.ironhack.bankingSystem.model.Account.Checking;
import com.ironhack.bankingSystem.model.Account.StudentChecking;
import com.ironhack.bankingSystem.repository.Accounts.CheckingRepository;
import com.ironhack.bankingSystem.repository.Accounts.StudentCheckingRepository;
import com.ironhack.bankingSystem.repository.Users.AccountHolderRepository;
import com.ironhack.bankingSystem.service.interfaces.Accounts.ICheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CheckingService implements ICheckingService {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;


        public Object create (CheckingDTO checkingDTO){

        Date date = new Date();
        Date dateBirth = accountHolderRepository.findById(checkingDTO.getPrimaryOwner()).get().getDateOfBirth();
        date.setYear(date.getYear()-24);

        if (dateBirth.before(date)) {
            Checking checking = new Checking();
            checking.setSecretKey(checkingDTO.getSecretKey());
            checking.setDateOfCreation(new Date());
            checking.setBalance(checkingDTO.getBalance());
            checking.setStatus(Status.ACTIVE);
            checking.setPrimaryOwner(accountHolderRepository.findById(checkingDTO.getPrimaryOwner()).get());
            //if(accountHolderRepository.findById(checkingDTO.getSecondaryOwner()).isEmpty()){
            if (checkingDTO.getSecondaryOwner()==null){
            }else{
                checking.setSecondaryOwner(accountHolderRepository.findById(checkingDTO.getSecondaryOwner()).get());
            }
            return checkingRepository.save(checking);
        }else{
            StudentChecking studentChecking = new StudentChecking();
            studentChecking.setSecretKey(checkingDTO.getSecretKey());
            studentChecking.setDateOfCreation(new Date());
            studentChecking.setBalance(checkingDTO.getBalance());
            studentChecking.setStatus(Status.ACTIVE);
            studentChecking.setPrimaryOwner(accountHolderRepository.findById(checkingDTO.getPrimaryOwner()).get());
            //if(accountHolderRepository.findById(checkingDTO.getSecondaryOwner()).isEmpty() ){
            if(checkingDTO.getSecondaryOwner()==null){
            }else{
                studentChecking.setSecondaryOwner(accountHolderRepository.findById(checkingDTO.getSecondaryOwner()).get());
            }
            return studentCheckingRepository.save(studentChecking);
        }
    }
}
