package com.ironhack.bankingSystem.controller.interfaces.others;

import com.ironhack.bankingSystem.controller.dto.CreditCardDTO;
import com.ironhack.bankingSystem.controller.dto.TransactionDTO;
import com.ironhack.bankingSystem.model.Account.CreditCard;
import com.ironhack.bankingSystem.model.others.Transaction;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ITransactionController {
    List<Transaction> getAll();
    public Transaction create (TransactionDTO transactionDTO, UserDetails userDetails);
}
