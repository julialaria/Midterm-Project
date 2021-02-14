package com.ironhack.bankingSystem.controller.impl.Users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bankingSystem.controller.dto.AccountHolderDTO;
import com.ironhack.bankingSystem.controller.dto.CheckingDTO;
import com.ironhack.bankingSystem.model.Account.Account;
import com.ironhack.bankingSystem.model.Account.CreditCard;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.Users.Admin;
import com.ironhack.bankingSystem.model.Users.Role;
import com.ironhack.bankingSystem.model.others.Address;
import com.ironhack.bankingSystem.model.others.Money;
import com.ironhack.bankingSystem.repository.Accounts.AccountRepository;
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
class AccountHolderControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
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


        }

        @AfterEach
        void tearDown () {

            accountRepository.deleteAll();
            roleRepository.deleteAll();
            accountHolderRepository.deleteAll();
            userRepository.deleteAll();
        }

        @Test
        void getAll_findAllAccountHolder() throws Exception {

                MvcResult result = mockMvc.perform(get("/accountHolder")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                        .andReturn();
                assertTrue(result.getResponse().getContentAsString().contains("Javier"));
                assertTrue(result.getResponse().getContentAsString().contains("Julia"));

            }

    @Test
    void findById_validId_returnAccountHolder() throws Exception {
        MvcResult result = mockMvc.perform(get("/accountHolder/by-id/" + accountHolderRepository.findAll().get(0).getId()).with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "admin1"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Julia"));
    }

    @Test
    void store_validAccountHolder_returnCreated() throws Exception {

        AccountHolderDTO accountHolderDTO = new AccountHolderDTO();
        accountHolderDTO.setName("Andrea");
        accountHolderDTO.setDateOfBirth(new Date(1994, 8, 31));
        accountHolderDTO.setUsername("andreaa");
        accountHolderDTO.setPassword("$2a$10$XZLkc4khf3SyiqtHeb1trekDiQxC17DWUX.J2Cx/tF/HdPqvL5Xoa");//123
        accountHolderDTO.setPrimaryAddress(new Address("Calle Colon", "Cadiz", "Spain", 11100));

        String body = objectMapper.writeValueAsString(accountHolderDTO);
        MvcResult result = mockMvc.perform(
                post("/create/accountHolder")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Andrea"));
    }
    }
