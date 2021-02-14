package com.ironhack.bankingSystem.controller.impl.Accounts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bankingSystem.controller.dto.CreditCardDTO;
import com.ironhack.bankingSystem.model.Account.Account;
import com.ironhack.bankingSystem.model.Account.Checking;
import com.ironhack.bankingSystem.model.Account.CreditCard;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.Users.Admin;
import com.ironhack.bankingSystem.model.Users.Role;
import com.ironhack.bankingSystem.model.others.Address;
import com.ironhack.bankingSystem.model.others.Money;
import com.ironhack.bankingSystem.repository.Accounts.AccountRepository;
import com.ironhack.bankingSystem.repository.Accounts.CheckingRepository;
import com.ironhack.bankingSystem.repository.Accounts.CreditCardRepository;
import com.ironhack.bankingSystem.repository.Users.AccountHolderRepository;
import com.ironhack.bankingSystem.repository.Users.AdminRepository;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CreditCardControllerTest {

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
    private CreditCardRepository creditCardRepository;
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

        CreditCard creditCard = new CreditCard();
        creditCard.setCreditLimit(new BigDecimal((300)));
        creditCard.setBalance(new Money(new BigDecimal((3400))));
        creditCard.setInterestRate(new BigDecimal((0.12)));
        creditCard.setPrimaryOwner(accountHolder);
        creditCardRepository.save(creditCard);

    }

        @AfterEach
        void tearDown(){

            accountRepository.deleteAll();
            roleRepository.deleteAll();
            accountHolderRepository.deleteAll();
            userRepository.deleteAll();
        }

    @Test
    void findAllCreditCard() throws Exception {
        MvcResult result = mockMvc.perform(get("/creditCard").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "admin1"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("0.12"));
    }

    @Test
    void findById_validId_returnCreditCard() throws Exception {

        List<CreditCard> creditCards = creditCardRepository.findAll();  // This is used to avoid problems with auto_increment ids.
        MvcResult result = mockMvc.perform(get("/creditCard/by-id/"+creditCards.get(0).getId())
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("300"));
    }

        @Test
        void create_validCreditCard_returnCreated() throws Exception {

            AccountHolder accountHolder4 = new AccountHolder();
            accountHolder4.setName("Andrea");
            accountHolder4.setDateOfBirth(new Date(1994, 8, 31));
            accountHolder4.setUsername("andreaa");
            accountHolder4.setPassword("$2a$10$XZLkc4khf3SyiqtHeb1trekDiQxC17DWUX.J2Cx/tF/HdPqvL5Xoa"); //123
            accountHolder4.setPrimaryAddress(new Address("Calle Colon", "Cadiz", "Spain", 11100));
            accountHolderRepository.save(accountHolder4);

            CreditCardDTO creditCardDTO = new CreditCardDTO();
            creditCardDTO.setCreditLimit(new BigDecimal((300)));
            creditCardDTO.setBalance(new Money(new BigDecimal((6400))));
            creditCardDTO.setInterestRate(new BigDecimal((0.19)));
            creditCardDTO.setPrimaryOwner(accountHolder4.getId());


            String body = objectMapper.writeValueAsString(creditCardDTO);
            MvcResult result = mockMvc.perform(
                    post("/create/creditCard")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertTrue(result.getResponse().getContentAsString().contains("0.19"));
        }

    }
