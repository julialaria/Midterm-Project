package com.ironhack.bankingSystem.controller.dto;

import com.ironhack.bankingSystem.model.others.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class CreditCardDTO {

    @DecimalMax(value = "100000")
    @DecimalMin(value = "100")
    private BigDecimal creditLimit = new BigDecimal((100));
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "0.2")
    private BigDecimal interestRate = new BigDecimal((0.2)).setScale(2, RoundingMode.HALF_UP);
    @NotNull(message = "A primary owner Id is required")
    private Integer primaryOwner;
    private Integer secondaryOwner;
    @NotNull (message = "An specific amount of balance and currency are required ")
    private Money balance;
    private Date dateOfCreation;

    public CreditCardDTO(){

    }

    public CreditCardDTO(@DecimalMax(value = "100000") @DecimalMin(value = "100") BigDecimal creditLimit,
                         @DecimalMin(value = "0.1") @DecimalMax(value = "0.2") BigDecimal interestRate,
                         @NotNull(message = "A primary owner Id is required")Integer primaryOwner,
                         Integer secondaryOwner, @NotNull (message = "An specific amount of balance and currency are required ")Money balance,
                         Date dateOfCreation) {

        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.balance = balance;
        this.dateOfCreation = new Date();
    }

    public CreditCardDTO(@DecimalMax(value = "100000") @DecimalMin(value = "100") BigDecimal creditLimit,
                         @DecimalMin(value = "0.1") @DecimalMax(value = "0.2") BigDecimal interestRate,
                         @NotNull(message = "A primary owner Id is required")Integer primaryOwner,
                         @NotNull (message = "An specific amount of balance and currency are required ")Money balance,
                         Date dateOfCreation) {

        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.primaryOwner = primaryOwner;
        this.balance = balance;
        this.dateOfCreation = new Date();
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
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
