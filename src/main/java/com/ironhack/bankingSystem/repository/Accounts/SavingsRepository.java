package com.ironhack.bankingSystem.repository.Accounts;

import com.ironhack.bankingSystem.model.Account.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Integer> {
}
