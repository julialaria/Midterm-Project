package com.ironhack.bankingSystem.repository.Users;

import com.ironhack.bankingSystem.model.Users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {

    AccountHolder findByUsername(String username);
}
