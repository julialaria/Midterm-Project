package com.ironhack.bankingSystem.controller.dto;

import com.ironhack.bankingSystem.model.Account.Account;
import com.ironhack.bankingSystem.model.others.Money;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TransactionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull (message = "The id of the origin account is required")
    private Integer origenAccountId;
    @NotNull (message = "The id of the destination account is required")
    private Integer destinationAccountId;
    @NotNull (message = "Please, specify a description for the transaction")
    private String description;
    @NotNull (message = "Please, specify an amount")
    private Money amount;
    private Date transactionDate;
    @NotNull (message = "The destination account`s owner name is required")
    private String nameOwnerDestinationAccount;


    public TransactionDTO(){

        this.transactionDate = new Date();

    }

    public TransactionDTO(@NotNull(message = "The id of the origin account is required")
                                  Integer origenAccountId,
                          @NotNull(message = "The id of the destination account is required")
                                  Integer destinationAccountId,
                          @NotNull(message = "Please, specify a description for the transaction")
                                  String description,
                          @NotNull(message = "Please, specify an amount") Money amount, Date transactionDate,
                          @NotNull(message = "The destination account`s owner name is required")
                                  String nameOwnerDestinationAccount) {

        this.origenAccountId = origenAccountId;
        this.destinationAccountId = destinationAccountId;
        this.description = description;
        this.amount = amount;
        this.transactionDate = new Date();
        this.nameOwnerDestinationAccount = nameOwnerDestinationAccount;

    }

    public Integer getId() {
        return id;
    }

    public Integer getOrigenAccountId() {
        return origenAccountId;
    }

    public void setOrigenAccountId(Integer origenAccountId) {
        this.origenAccountId = origenAccountId;
    }

    public Integer getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(Integer destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getNameOwnerDestinationAccount() {
        return nameOwnerDestinationAccount;
    }

    public void setNameOwnerDestinationAccount(String nameOwnerDestinationAccount) {
        this.nameOwnerDestinationAccount = nameOwnerDestinationAccount;
    }

}
