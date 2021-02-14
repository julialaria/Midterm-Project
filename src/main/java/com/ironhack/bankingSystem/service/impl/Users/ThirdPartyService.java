package com.ironhack.bankingSystem.service.impl.Users;

import com.ironhack.bankingSystem.controller.dto.ThirdPartyDTO;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.Users.ThirdParty;
import com.ironhack.bankingSystem.repository.Users.ThirdPartyRepository;
import com.ironhack.bankingSystem.service.interfaces.Users.IThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ThirdPartyService implements IThirdPartyService {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Override
    public List<ThirdParty> getAll() {
        return thirdPartyRepository.findAll();
    }

    public ThirdParty findById(Integer id) {
        if(!thirdPartyRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Third Party user not found");
        }
        return thirdPartyRepository.findById(id).get();
    }

    public ThirdParty create (ThirdPartyDTO thirdPartyDTO) {
        ThirdParty thirdParty = new ThirdParty();
        thirdParty.setName(thirdPartyDTO.getName());
        thirdParty.setHashedKey(thirdPartyDTO.getHashedKey());
        return thirdPartyRepository.save(thirdParty);
    }
}
