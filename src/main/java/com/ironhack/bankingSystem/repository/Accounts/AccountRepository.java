package com.ironhack.bankingSystem.repository.Accounts;

import com.ironhack.bankingSystem.model.Account.Account;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByPrimaryOwner(AccountHolder user);
}
