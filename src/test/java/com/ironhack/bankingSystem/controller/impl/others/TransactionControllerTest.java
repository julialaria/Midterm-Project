package com.ironhack.bankingSystem.controller.impl.others;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bankingSystem.controller.dto.CreditCardDTO;
import com.ironhack.bankingSystem.controller.dto.TransactionDTO;
import com.ironhack.bankingSystem.enums.Status;
import com.ironhack.bankingSystem.model.Account.Account;
import com.ironhack.bankingSystem.model.Account.Checking;
import com.ironhack.bankingSystem.model.Account.Savings;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.Users.Admin;
import com.ironhack.bankingSystem.model.Users.Role;
import com.ironhack.bankingSystem.model.Users.ThirdParty;
import com.ironhack.bankingSystem.model.others.Address;
import com.ironhack.bankingSystem.model.others.Money;
import com.ironhack.bankingSystem.model.others.Transaction;
import com.ironhack.bankingSystem.repository.Accounts.AccountRepository;
import com.ironhack.bankingSystem.repository.Accounts.CheckingRepository;
import com.ironhack.bankingSystem.repository.Accounts.SavingsRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TransactionControllerTest {
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
    private SavingsRepository savingsRepository;
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

        Transaction transaction = new Transaction();
        transaction.setAmount(new Money(new BigDecimal(200)));
        transaction.setDescription("rent");
        transaction.setDestinationAccount(account);
        transaction.setOrigenAccount(account2);
        transactionRepository.save(transaction);

        Savings savings = new Savings();
        savings.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe");//secretKey
        savings.setMinimumBalance(new BigDecimal((400)));
        savings.setInterestRate(new BigDecimal((0.3)));
        savings.setBalance(new Money(new BigDecimal(5000)));
        savings.setPrimaryOwner(accountHolder);
        savings.setStatus(Status.ACTIVE);
        savingsRepository.save(savings);

        Savings savings1 = new Savings();
        savings1.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe");//secretKey
        savings1.setMinimumBalance(new BigDecimal((500)));
        savings1.setInterestRate(new BigDecimal((0.2)));
        savings1.setBalance(new Money(new BigDecimal(8000)));
        savings1.setStatus(Status.ACTIVE);
        savings1.setPrimaryOwner(accountHolder);
        savingsRepository.save(savings1);

    }

    @AfterEach
    void tearDown() {

        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getAll_findAllTransactions() throws Exception {
        MvcResult result = mockMvc.perform(get("/transactions")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("rent"));

    }

    @Test
    void create_validTransaction_returnCreated() throws Exception {

        AccountHolder accountHolder4 = new AccountHolder();
        accountHolder4.setName("Andrea");
        accountHolder4.setDateOfBirth(new Date(1994, 8, 31));
        accountHolder4.setUsername("andreaa");
        accountHolder4.setPassword("$2a$10$XZLkc4khf3SyiqtHeb1trekDiQxC17DWUX.J2Cx/tF/HdPqvL5Xoa"); //123
        accountHolder4.setPrimaryAddress(new Address("Calle Colon", "Cadiz", "Spain", 11100));
        accountHolderRepository.save(accountHolder4);

        Role role = new Role();
        role.setName("ACCOUNTHOLDER");
        role.setUser(accountHolder4);
        roleRepository.save(role);

        Account account = new Account();
        account.setBalance(new Money(new BigDecimal("1600")));
        account.setPrimaryOwner(accountHolder4);
        accountRepository.save(account);

        Account account2 = new Account();
        account2.setBalance(new Money(new BigDecimal("300")));
        account2.setPrimaryOwner(accountHolder4);
        accountRepository.save(account2);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setOrigenAccountId(account.getId());
        transactionDTO.setDestinationAccountId(account2.getId());
        transactionDTO.setNameOwnerDestinationAccount("Andrea");
        transactionDTO.setDescription("expenses");
        transactionDTO.setAmount(new Money(new BigDecimal(450)));


        String body = objectMapper.writeValueAsString(transactionDTO);
        MvcResult result = mockMvc.perform(
                post("/transaction")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("andreaa", "123")))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("expenses"));

    }

    @Test
    void create_fraudChecker_MultipleTransactionOneSecond() throws Exception {

        List<Savings> savings = savingsRepository.findAll();
        Savings originAccount = savings.get(0);
        Savings destinationAccount = savings.get(1);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setOrigenAccountId(originAccount.getId());
        transactionDTO.setDestinationAccountId(destinationAccount.getId());
        transactionDTO.setAmount(new Money(new BigDecimal (200)));
        transactionDTO.setDescription("expenses");
        transactionDTO.setNameOwnerDestinationAccount(destinationAccount.getPrimaryOwner().getName());
        transactionDTO.setTransactionDate(new Date());

        String body = objectMapper.writeValueAsString(transactionDTO);
        MvcResult result =mockMvc.perform(
                post("/transaction")
                        .content(body).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("julialaria", "123")))
                .andExpect(status().isCreated())
                .andReturn();

        TransactionDTO transactionDTO2 = new TransactionDTO();
        transactionDTO2.setOrigenAccountId(originAccount.getId());
        transactionDTO2.setDestinationAccountId(destinationAccount.getId());
        transactionDTO2.setAmount(new Money(BigDecimal.valueOf(400)));
        transactionDTO2.setDescription("more expenses");
        transactionDTO2.setNameOwnerDestinationAccount(destinationAccount.getPrimaryOwner().getName());
        transactionDTO2.setTransactionDate(new Date(transactionDTO.getTransactionDate().getTime()+500));

        String body2 = objectMapper.writeValueAsString(transactionDTO2);
        MvcResult result2 =mockMvc.perform(
                post("/transaction")
                        .content(body2).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("julialaria", "123")))
                .andExpect(status().isCreated())
                .andReturn();

        TransactionDTO transactionDTO3 = new TransactionDTO();
        transactionDTO3.setOrigenAccountId(originAccount.getId());
        transactionDTO3.setDestinationAccountId(destinationAccount.getId());
        transactionDTO3.setAmount(new Money(BigDecimal.valueOf(600)));
        transactionDTO3.setDescription("more and more expenses");
        transactionDTO3.setNameOwnerDestinationAccount(destinationAccount.getPrimaryOwner().getName());
        transactionDTO3.setTransactionDate(new Date(transactionDTO2.getTransactionDate().getTime()+500));

        String body3 = objectMapper.writeValueAsString(transactionDTO3);
        MvcResult result3 =mockMvc.perform(
                post("/transaction")
                        .content(body3).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("julialaria", "123")))
                .andExpect(status().isForbidden())
                .andReturn();
    }

}