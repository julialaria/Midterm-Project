package com.ironhack.bankingSystem.controller.impl.Accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bankingSystem.controller.dto.CheckingDTO;
import com.ironhack.bankingSystem.model.Account.Account;
import com.ironhack.bankingSystem.model.Account.Checking;
import com.ironhack.bankingSystem.model.Account.StudentChecking;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.Users.Admin;
import com.ironhack.bankingSystem.model.Users.Role;
import com.ironhack.bankingSystem.model.others.Address;
import com.ironhack.bankingSystem.model.others.Money;
import com.ironhack.bankingSystem.repository.Accounts.AccountRepository;
import com.ironhack.bankingSystem.repository.Accounts.CheckingRepository;
import com.ironhack.bankingSystem.repository.Users.AccountHolderRepository;
import com.ironhack.bankingSystem.repository.Users.AdminRepository;
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

import static org.aspectj.apache.bcel.Repository.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CheckingControllerTest {

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

        Checking checking = new Checking();
        checking.setBalance((new Money(new BigDecimal("6200"))));
        checking.setPrimaryOwner(accountHolder);
        checking.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        checkingRepository.save(checking);
    }

    @AfterEach
    void tearDown() {

        accountRepository.deleteAll();
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findAllChecking() throws Exception {
        MvcResult result = mockMvc.perform(get("/checking").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "admin1"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("6200"));
    }


    @Test
    void findById_validId_returnChecking() throws Exception {

        List<Checking> checking = checkingRepository.findAll();  // This is used to avoid problems with auto_increment ids.
        MvcResult result = mockMvc.perform(get("/checking/by-id/"+checking.get(0).getId())
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("6200"));
    }


    @Test
    void create_validChecking_returnCreated() throws Exception {

        AccountHolder accountHolder4 = new AccountHolder();
        accountHolder4.setName("Andrea");
        accountHolder4.setDateOfBirth(new Date(1994, 8, 31));
        accountHolder4.setUsername("andreaa");
        accountHolder4.setPassword("$2a$10$XZLkc4khf3SyiqtHeb1trekDiQxC17DWUX.J2Cx/tF/HdPqvL5Xoa"); //123
        accountHolder4.setPrimaryAddress(new Address("Calle Colon", "Cadiz", "Spain", 11100));
        accountHolderRepository.save(accountHolder4);

        CheckingDTO checkingDTO = new CheckingDTO();
        checkingDTO.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe");
        checkingDTO.setPrimaryOwner(accountHolder4.getId());
        checkingDTO.setBalance((new Money(new BigDecimal("1300"))));

        String body = objectMapper.writeValueAsString(checkingDTO);
        MvcResult result = mockMvc.perform(
                post("/create/checking")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                        .andExpect(status().isCreated())
                        .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("1300"));
    }

    @Test
    void create_lessThan24_returnStudentChecking() throws Exception {

        AccountHolder accountHolder5 = new AccountHolder();
        accountHolder5.setName("Alvaro");
        accountHolder5.setDateOfBirth(new Date(2015, 8, 31));
        accountHolder5.setUsername("alvaroo");
        accountHolder5.setPassword("$2a$10$XZLkc4khf3SyiqtHeb1trekDiQxC17DWUX.J2Cx/tF/HdPqvL5Xoa"); //123
        accountHolder5.setPrimaryAddress(new Address("Calle Andorra", "Andorra", "Spain", 11200));
        accountHolderRepository.save(accountHolder5);

        CheckingDTO checkingDTO = new CheckingDTO();
        checkingDTO.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe");
        checkingDTO.setPrimaryOwner(accountHolder5.getId());
        checkingDTO.setBalance((new Money(new BigDecimal("1300"))));

        StudentChecking studentChecking = new StudentChecking();

        String body = objectMapper.writeValueAsString(checkingDTO);
        MvcResult result = mockMvc.perform(
                post("/create/checking")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("1300"));
        assertFalse(result.getResponse().getContentAsString().contains("monthlyMaintenanceFee"));
        assertFalse(result.getResponse().getContentAsString().contains("minimumBalance"));

    }
}