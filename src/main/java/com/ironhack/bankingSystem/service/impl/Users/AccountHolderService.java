package com.ironhack.bankingSystem.service.impl.Users;

import com.ironhack.bankingSystem.controller.dto.AccountHolderDTO;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.Users.Role;
import com.ironhack.bankingSystem.model.Users.ThirdParty;
import com.ironhack.bankingSystem.repository.Users.AccountHolderRepository;
import com.ironhack.bankingSystem.repository.utils.RoleRepository;
import com.ironhack.bankingSystem.repository.utils.UserRepository;
import com.ironhack.bankingSystem.service.interfaces.Users.IAccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountHolderService implements IAccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<AccountHolder> getAll() {
        return accountHolderRepository.findAll();
    }

    public AccountHolder findById(Integer id) {
        if(!accountHolderRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found");
        }
        return accountHolderRepository.findById(id).get();
    }

    @Override
    public AccountHolder store (AccountHolderDTO accountHolderDTO) {
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName(accountHolderDTO.getName());
        accountHolder.setPrimaryAddress(accountHolderDTO.getPrimaryAddress());
       if(accountHolderDTO.getMailingAddress()==null){

       }else{
           accountHolder.setMailingAddress(accountHolderDTO.getMailingAddress());
       }
        accountHolder.setDateOfBirth(accountHolderDTO.getDateOfBirth());
        accountHolder.setUsername(accountHolderDTO.getUsername());
        accountHolder.setPassword(accountHolderDTO.getPassword());
        userRepository.save(accountHolder);
        Role role = new Role("ACCOUNTHOLDER", accountHolder);
        roleRepository.save(role);
        return accountHolderRepository.save(accountHolder);
    }
}
