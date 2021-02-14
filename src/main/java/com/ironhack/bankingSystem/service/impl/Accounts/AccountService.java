package com.ironhack.bankingSystem.service.impl.Accounts;

import com.ironhack.bankingSystem.controller.dto.AccountDTO;
import com.ironhack.bankingSystem.controller.dto.StatusDTO;
import com.ironhack.bankingSystem.enums.Status;
import com.ironhack.bankingSystem.enums.TransactionType;
import com.ironhack.bankingSystem.model.Account.*;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.Users.Admin;
import com.ironhack.bankingSystem.model.Users.ThirdParty;
import com.ironhack.bankingSystem.model.Users.User;
import com.ironhack.bankingSystem.model.others.Money;
import com.ironhack.bankingSystem.repository.Accounts.*;
import com.ironhack.bankingSystem.repository.Users.AccountHolderRepository;
import com.ironhack.bankingSystem.repository.Users.AdminRepository;
import com.ironhack.bankingSystem.repository.Users.ThirdPartyRepository;
import com.ironhack.bankingSystem.service.interfaces.Accounts.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;


    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    public List<Account> getAllAccountsByUsername(String username) {

        AccountHolder user = accountHolderRepository.findByUsername(username);
        List<Account> accounts = accountRepository.findByPrimaryOwner(user);

        for (Account account : accounts){
            if (account instanceof Savings){
                Savings saving = (Savings) account;
                Date dateNow = new Date();
                int years=0;
                saving.getLastInterestDate().setYear(saving.getLastInterestDate().getYear()+1);
                Date lastDate = saving.getLastInterestDate();
                while(lastDate.before(dateNow)){
                    years++;
                    lastDate.setYear(lastDate.getYear()+1);
                }
                lastDate.setYear(lastDate.getYear()-1);
                saving.setLastInterestDate(lastDate);
                BigDecimal interest = new BigDecimal(saving.getInterestRate().doubleValue());
                interest = interest.add(new BigDecimal(1));
                interest = interest.pow(years);

                saving.setBalance(new Money(saving.getBalance().getAmount().multiply(interest)));
                saving.setLastInterestDate(lastDate);
                savingsRepository.save(saving);
            }

            if (account instanceof CreditCard){
                CreditCard creditCard = (CreditCard) account;
                Date dateNow = new Date();
                int months = 0;
                creditCard.getLastInterestDate().setMonth(creditCard.getLastInterestDate().getMonth()+1);
                Date lastDate = creditCard.getLastInterestDate();
                while(lastDate.before(dateNow)){
                    months++;
                    lastDate.setMonth(lastDate.getMonth()+1);
                }
                lastDate.setMonth(lastDate.getMonth()-1);
                creditCard.setLastInterestDate(lastDate);
                BigDecimal interest = new BigDecimal(creditCard.getInterestRate().doubleValue());
                interest = interest.divide(new BigDecimal(12),2,RoundingMode.HALF_UP);
                interest = interest.add (new BigDecimal(1));
                interest = interest.pow (months);

                creditCard.setBalance(new Money(creditCard.getBalance().getAmount().multiply(interest)));
                creditCard.setLastInterestDate(lastDate);
                creditCardRepository.save(creditCard);
            }

            if (account instanceof  Checking){
                Checking checking  = (Checking) account;
                Date dateNow = new Date();
                int months = 0;
                checking.getLastMaintenanceFee().setMonth(checking.getLastMaintenanceFee().getMonth()+1);
                Date lastDate = checking.getLastMaintenanceFee();
                while(lastDate.before(dateNow)){
                    months++;
                    lastDate.setMonth(lastDate.getMonth()+1);
                }
                lastDate.setMonth(lastDate.getMonth()-1);
                checking.setLastMaintenanceFee(lastDate);
                BigDecimal montlyFee = checking.getMonthlyMaintenanceFee().getAmount();
                montlyFee = montlyFee.multiply(new BigDecimal(months));
                checking.setBalance(new Money(checking.getBalance().decreaseAmount(new Money(montlyFee))));
                checking.setLastMaintenanceFee(lastDate);
                checkingRepository.save(checking);
            }

        }

        return accounts;
    }


    public void updateBalance(Integer id, Money balance) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            account.get().setBalance(balance);
            accountRepository.save(account.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

    public void update (String hashedKey, AccountDTO accountDTO) {
        Optional<Account> account = accountRepository.findById(accountDTO.getId());
        Optional <ThirdParty> thirdParty = thirdPartyRepository.findByHashedKey(hashedKey);

        if (account.isPresent() && thirdParty.isPresent()) {
            if (account.get() instanceof Savings) {
                if (((Savings) account.get()).getSecretKey().equals(accountDTO.getSecretKey())){
                    if (accountDTO.getTransactionType().equals(TransactionType.SEND)){
                        account.get().setBalance(new Money(account.get().getBalance().increaseAmount(accountDTO.getAmount())));
                    }else {
                        account.get().setBalance(new Money(account.get().getBalance().decreaseAmount(accountDTO.getAmount())));
                    }
                }else {
                    throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "The secretKey is incorrect");
                }

            }else if (account.get() instanceof Checking) {
                if (((Checking) account.get()).getSecretKey().equals(accountDTO.getSecretKey())){
                    if (accountDTO.getTransactionType().equals(TransactionType.SEND)){
                        account.get().setBalance(new Money(account.get().getBalance().increaseAmount(accountDTO.getAmount())));
                    }else {
                        account.get().setBalance(new Money(account.get().getBalance().decreaseAmount(accountDTO.getAmount())));
                    }
                }else {

                    throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "The secretKey is incorrect");
                }

            }else if (account.get() instanceof StudentChecking) {
                if (((StudentChecking) account.get()).getSecretKey().equals(accountDTO.getSecretKey())) {
                    if (accountDTO.getTransactionType().equals(TransactionType.SEND)) {
                        account.get().setBalance(new Money(account.get().getBalance().increaseAmount(accountDTO.getAmount())));
                    } else {
                        account.get().setBalance(new Money(account.get().getBalance().decreaseAmount(accountDTO.getAmount())));
                    }
                } else {
                    throw new IllegalArgumentException("The secretKey is incorrect");
                }
            }else{

                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The type account is not correct");
            }

            accountRepository.save(account.get());

        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account or Third Party not found");
        }
    }


    public void updateStatus(Integer id, Status status) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            if (account.get() instanceof CreditCard) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit Card do not have a state");
            }
            account.get().setId(id);
            if (account.get() instanceof Savings) {
                ((Savings) account.get()).setStatus(status);
                accountRepository.save(account.get());
                savingsRepository.save((Savings) account.get());
            }
            if (account.get() instanceof Checking) {
                ((Checking) account.get()).setStatus(status);
                accountRepository.save(account.get());
                checkingRepository.save((Checking) account.get());
            }
            if (account.get() instanceof StudentChecking) {
                ((StudentChecking) account.get()).setStatus(status);
                accountRepository.save(account.get());
                studentCheckingRepository.save((StudentChecking) account.get());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

}
