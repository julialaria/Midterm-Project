package com.ironhack.bankingSystem.repository.Accounts;

import com.ironhack.bankingSystem.model.Account.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
}
