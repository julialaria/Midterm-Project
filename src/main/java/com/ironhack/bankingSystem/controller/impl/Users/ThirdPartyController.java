package com.ironhack.bankingSystem.controller.impl.Users;

import com.ironhack.bankingSystem.controller.dto.SavingsDTO;
import com.ironhack.bankingSystem.controller.dto.ThirdPartyDTO;
import com.ironhack.bankingSystem.controller.interfaces.Users.IThirdPartyController;
import com.ironhack.bankingSystem.model.Account.Savings;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.Users.ThirdParty;
import com.ironhack.bankingSystem.repository.Users.ThirdPartyRepository;
import com.ironhack.bankingSystem.service.interfaces.Accounts.ISavingsService;
import com.ironhack.bankingSystem.service.interfaces.Users.IThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ThirdPartyController implements IThirdPartyController {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    IThirdPartyService thirdPartyService;

    @GetMapping("/thirdParty")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> getAll() {
        return thirdPartyService.getAll();
    }

    @GetMapping("/thirdParty/by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThirdParty findById(@PathVariable Integer id) {
        return thirdPartyService.findById(id);
    }

    @PostMapping("/create/thirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty create(@RequestBody @Valid ThirdPartyDTO thirdPartyDTO) {
        return thirdPartyService.create (thirdPartyDTO);
    }


}
