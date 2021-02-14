package com.ironhack.bankingSystem.controller.dto;

import com.ironhack.bankingSystem.enums.Status;
import com.ironhack.bankingSystem.model.Account.Savings;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.others.Money;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

public class SavingsDTO {

    @NotNull(message = "A primary owner Id is required")
    private Integer primaryOwner;
    private Integer secondaryOwner;
    @NotNull(message = "A secret key is required")
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;
    @DecimalMax(value = "1000", message = "The minimumBalance must be less than 1000")
    @DecimalMin(value = "100", message = "The minimumBalance must be greater then 100")
    private BigDecimal minimumBalance = new BigDecimal(1000);
    @DecimalMax(value = "0.5", message = "The interestRate can not be greater than 0.5")
    @Positive(message = "The interestRate must be positive")
    private BigDecimal interestRate = new BigDecimal(0.0025);
    @NotNull (message = "An specific amount of balance and currency are required ")
    private Money balance;
    private Date dateOfCreation;

    public SavingsDTO(){
    }

    public SavingsDTO( @NotNull(message = "A primary owner Id is required") Integer primaryOwner,
                       Integer secondaryOwner, @NotNull (message = "A secret key is required") String secretKey,
                       Status status,
                       @DecimalMax(value = "1000", message = "The minimumBalance must be 1000 or more")
                       @DecimalMin(value = "100", message = "The minimumBalance must be 100 or more") BigDecimal minimumBalance,
                       @DecimalMax(value = "0.5", message = "The interestRate can not be greater than 0.5") @Positive(message = "The interestRate must be positive") BigDecimal interestRate,
                       @NotNull (message = "An specific amount of balance and currency are required ")Money balance, Date dateOfCreation) {

        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.balance = balance;
        this.dateOfCreation = new Date();
    }

  public SavingsDTO( @NotNull(message = "A primary owner Id is required") Integer primaryOwner,
                       @NotNull (message = "A secret key is required") String secretKey,
                       Status status,
                       @DecimalMax(value = "1000", message = "The minimumBalance must be 1000 or more")
                       @DecimalMin(value = "100", message = "The minimumBalance must be 100 or more") BigDecimal minimumBalance,
                       @DecimalMax(value = "0.5", message = "The interestRate can not be greater than 0.5") @Positive(message = "The interestRate must be positive") BigDecimal interestRate,
                       @NotNull (message = "An specific amount of balance and currency are required ")Money balance, Date dateOfCreation) {

        this.primaryOwner = primaryOwner;
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.balance = balance;
        this.dateOfCreation = new Date();
    }

    public Integer getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(Integer primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public Integer getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(Integer secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}

