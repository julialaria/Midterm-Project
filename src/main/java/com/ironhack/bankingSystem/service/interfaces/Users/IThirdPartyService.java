package com.ironhack.bankingSystem.service.interfaces.Users;

import com.ironhack.bankingSystem.controller.dto.ThirdPartyDTO;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.Users.ThirdParty;

import java.util.List;
import java.util.Optional;

public interface IThirdPartyService {
    List<ThirdParty> getAll();
    public ThirdParty findById(Integer id);
    ThirdParty create (ThirdPartyDTO thirdPartyDTO);
}
