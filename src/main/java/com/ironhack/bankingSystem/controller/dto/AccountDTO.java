package com.ironhack.bankingSystem.controller.dto;

import com.ironhack.bankingSystem.enums.TransactionType;
import com.ironhack.bankingSystem.model.others.Money;
import com.ironhack.bankingSystem.model.others.Transaction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class AccountDTO {

    @NotNull (message = "Please, specify the amount to transfer")
    @Positive (message = "The amount must be positive")
    private BigDecimal amount;
    @NotNull (message = "Please, specify the id of the account")
    private Integer id;
    @NotNull (message = "Please, specify the secretKey of the account")
    private String secretKey;
    @Enumerated(EnumType.STRING)
    @NotNull (message = "Please, specify Send or Receive for the type of transaction")
    private TransactionType transactionType;

    public AccountDTO(){

    }

    public AccountDTO(@NotNull(message = "Please, specify the amount to transfer") BigDecimal amount,
                      @NotNull(message = "Please, specify the id of the account") Integer id,
                      @NotNull (message = "Please, specify Send or Receive for the type of transaction") TransactionType transactionType,
                      @NotNull(message = "Please, specify the secretKey of the account") String secretKey) {
        this.amount = amount;
        this.id = id;
        this.secretKey = secretKey;
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
