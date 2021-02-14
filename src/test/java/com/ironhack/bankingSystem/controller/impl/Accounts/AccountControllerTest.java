package com.ironhack.bankingSystem.controller.impl.Accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bankingSystem.controller.dto.AccountDTO;
import com.ironhack.bankingSystem.controller.dto.BalanceDTO;
import com.ironhack.bankingSystem.controller.dto.StatusDTO;
import com.ironhack.bankingSystem.controller.dto.TransactionDTO;
import com.ironhack.bankingSystem.enums.Status;
import com.ironhack.bankingSystem.enums.TransactionType;
import com.ironhack.bankingSystem.model.Account.Account;
import com.ironhack.bankingSystem.model.Account.Checking;
import com.ironhack.bankingSystem.model.Account.Savings;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.Users.Admin;
import com.ironhack.bankingSystem.model.Users.Role;
import com.ironhack.bankingSystem.model.Users.ThirdParty;
import com.ironhack.bankingSystem.model.others.Address;
import com.ironhack.bankingSystem.model.others.Money;
import com.ironhack.bankingSystem.repository.Accounts.AccountRepository;
import com.ironhack.bankingSystem.repository.Accounts.CheckingRepository;
import com.ironhack.bankingSystem.repository.Users.AccountHolderRepository;
import com.ironhack.bankingSystem.repository.Users.AdminRepository;
import com.ironhack.bankingSystem.repository.Users.ThirdPartyRepository;
import com.ironhack.bankingSystem.repository.others.TransactionRepository;
import com.ironhack.bankingSystem.repository.utils.RoleRepository;
import com.ironhack.bankingSystem.repository.utils.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName("Julia");
        accountHolder.setDateOfBirth(new Date(1994, 8, 31));
        accountHolder.setUsername("julialaria");
        accountHolder.setPassword("$2a$10$XZLkc4khf3SyiqtHeb1trekDiQxC17DWUX.J2Cx/tF/HdPqvL5Xoa"); //123
        accountHolder.setPrimaryAddress(new Address("Calle Colon", "Cadiz", "Spain", 11100));
        accountHolderRepository.save(accountHolder);

        AccountHolder accountHolder2 = new AccountHolder();
        accountHolder2.setName("Javier");
        accountHolder2.setDateOfBirth(new Date(1980, 10, 28));
        accountHolder2.setUsername("javierpdg");
        accountHolder2.setPassword("$2a$10$Vpmtb2fZMzBQjLNZ5CadSOyfQQku6jYCjBXEU/uPcj9cM8uABpoBW"); //1234
        accountHolder2.setPrimaryAddress(new Address("Calle America", "Madrid", "Spain", 5002));
        accountHolderRepository.save(accountHolder2);

        Admin admin = new Admin();
        admin.setName("Maria");
        admin.setUsername("admin1");
        admin.setPassword("$2a$10$NcfKcfldbLAEojIUdYgzSujzMgWH6hbCnw7y92FDgSsof/Mg/3MhW"); //admin1

        Role role = new Role();
        role.setName("ACCOUNTHOLDER");
        role.setUser(accountHolder);

        Role role2 = new Role();
        role2.setName("ACCOUNTHOLDER");
        role2.setUser(accountHolder);

        Role role3 = new Role();
        role3.setName("ADMIN");
        role3.setUser(admin);

        userRepository.saveAll(List.of(accountHolder, accountHolder2, admin));
        roleRepository.save(role);
        roleRepository.save(role2);
        roleRepository.save(role3);

        Account account = new Account();
        account.setBalance(new Money(new BigDecimal("1200")));
        account.setPrimaryOwner(accountHolder);
        accountRepository.save(account);

        Account account2 = new Account();
        account2.setBalance(new Money(new BigDecimal("3500")));
        account2.setPrimaryOwner(accountHolder);
        accountRepository.save(account2);

        Checking checkings = new Checking();
        checkings.setBalance(new Money(new BigDecimal(4300)));
        checkings.setPrimaryOwner(accountHolder2);
        checkings.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe");//secretKey
        checkingRepository.save(checkings);

        ThirdParty thirdParty = new ThirdParty();
        thirdParty.setName("Foreign Bank");
        thirdParty.setHashedKey("$2a$10$Kh2KTGKiIU/DKmVMkkL6ZOKa1fFyPVU2JOfRfuoLFU5eKY/D9oONC");//hashedKey
        thirdPartyRepository.save(thirdParty);
    }

    @AfterEach
    void tearDown() {

        thirdPartyRepository.deleteAll();
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();

    }

    @Test
    void getAll_NoParams_returnAll() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts/by-username")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("julialaria", "123")))
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1200"));
        assertTrue(result.getResponse().getContentAsString().contains("3500"));

    }

    @Test
    void updateBalance() throws Exception {
        List<Account> accounts = accountRepository.findAll();
        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setBalance(new Money(new BigDecimal(3400)));
        String body = objectMapper.writeValueAsString(balanceDTO);
        MvcResult result = mockMvc.perform(patch("/updateBalance/"+ accounts.get(0).getId())
                .content(body).contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isNoContent())
                .andReturn();

    }

    @Test
    void updateStatus() throws Exception {
        List<Account> accounts = accountRepository.findAll();
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setStatus(Status.ACTIVE);
        String body = objectMapper.writeValueAsString(statusDTO);
        MvcResult result = mockMvc.perform(patch("/updateStatus/"+ accounts.get(0).getId())
                .content(body).contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isNoContent())
                .andReturn();

    }

    @Test
    void update_thirdPartyTransaction_send() throws Exception {
        List<Checking> checkings = checkingRepository.findAll();
        List<ThirdParty> thirdParties = thirdPartyRepository.findAll();
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setTransactionType(TransactionType.SEND);
        accountDTO.setAmount(new BigDecimal(200));
        accountDTO.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe");//secretKey
        accountDTO.setId(checkings.get(0).getId());

        String body = objectMapper.writeValueAsString(accountDTO);
        MvcResult result = mockMvc.perform(patch("/update?hashedKey=" +thirdParties.get(0).getHashedKey())
                .content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();


    }

    @Test
    void update_thirdPartyTransaction_receive() throws Exception {
        List<Checking> checkings = checkingRepository.findAll();
        List<ThirdParty> thirdParties = thirdPartyRepository.findAll();
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setTransactionType(TransactionType.RECEIVE);
        accountDTO.setAmount(new BigDecimal(400));
        accountDTO.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe");//secretKey
        accountDTO.setId(checkings.get(0).getId());

        String body = objectMapper.writeValueAsString(accountDTO);
        MvcResult result = mockMvc.perform(patch("/update?hashedKey=" +thirdParties.get(0).getHashedKey())
                .content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();


    }
}