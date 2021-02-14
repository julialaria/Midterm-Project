package com.ironhack.bankingSystem.repository.Accounts;

import com.ironhack.bankingSystem.model.Account.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Integer> {
}
