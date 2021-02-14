package com.ironhack.bankingSystem.controller.dto;

import com.ironhack.bankingSystem.enums.Status;
import com.ironhack.bankingSystem.model.Account.Checking;
import com.ironhack.bankingSystem.model.others.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class CheckingDTO {

    @NotNull(message = "A primary owner Id is required")
    private Integer primaryOwner;
    private Integer secondaryOwner;
    @NotNull (message = "An specific amount of balance and currency are required ")
    private Money balance;
    private Date dateOfCreation;
    @NotNull(message = "A secretKey is required")
    private String secretKey;
    private final BigDecimal minimumBalance = new BigDecimal(250);
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_maintenance_currency"))
    })
    private final Money monthlyMaintenanceFee = new Money(new BigDecimal(12));
    @Enumerated(EnumType.STRING)
    private Status status;
    private Date lastMaintenanceFee;

    public CheckingDTO(){
        this.lastMaintenanceFee = new Date();

    }

    public CheckingDTO( @NotNull(message = "A primary owner Id is required")Integer primaryOwner,
                        Integer secondaryOwner,
                        @NotNull (message = "An specific amount of balance and currency are required ")Money balance,
                        Date dateOfCreation,
                       @NotNull(message = "A secretKey is required") String secretKey) {

        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.balance = balance;
        this.dateOfCreation = new Date();
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
        this.lastMaintenanceFee= new Date();
    }

    public CheckingDTO( @NotNull(message = "A primary owner Id is required")Integer primaryOwner,
                        @NotNull (message = "An specific amount of balance and currency are required ")Money balance,
                        Date dateOfCreation,
                        @NotNull(message = "A secretKey is required") String secretKey) {
        this.primaryOwner = primaryOwner;
        this.balance = balance;
        this.dateOfCreation = new Date();
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
        this.lastMaintenanceFee = new Date();
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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getLastMaintenanceFee() {
        return lastMaintenanceFee;
    }

    public void setLastMaintenanceFee(Date lastMaintenanceFee) {
        this.lastMaintenanceFee = lastMaintenanceFee;
    }


}
