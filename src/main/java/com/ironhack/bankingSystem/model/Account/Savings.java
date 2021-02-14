package com.ironhack.bankingSystem.model.Account;

import com.ironhack.bankingSystem.enums.Status;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.others.Money;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class Savings extends Account {
    @NotNull (message = "A secretKey is required")
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;
    @DecimalMax(value = "1000", message = "The minimumBalance must be less than 1000")
    @DecimalMin(value = "100", message = "The minimumBalance must be greater then 100")
    private BigDecimal minimumBalance = new BigDecimal(1000);
    @DecimalMax(value = "0.5", message = "The interestRate can not be greater than 0.5")
    @Positive(message = "The interestRate must be positive")
    private BigDecimal interestRate = new BigDecimal(0.0025);
    private Date lastInterestDate;

    public Savings(){
        this.lastInterestDate = new Date();
    }

    public Savings(@NotNull(message = "A balance is required") Money balance,
                   @NotNull(message = "A primaryOwner is required") AccountHolder primaryOwner,
                   AccountHolder secondaryOwner,
                   @NotNull(message = "A secretKey is required") String secretKey) {
        super(balance, primaryOwner, secondaryOwner);
        this.lastInterestDate=new Date();
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
    }

    public Savings(@NotNull(message = "A balance is required")Money balance,
                   @NotNull(message = "A primaryOwner is required")AccountHolder primaryOwner,
                   AccountHolder secondaryOwner,
                   @NotNull(message = "A secretKey is required")String secretKey,
                   @DecimalMax(value = "0.5", message = "The interestRate can not be greater than 0.5")
                   @Positive(message = "The interestRate must be positive")BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        this.lastInterestDate=new Date();
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
        this.interestRate = interestRate;
    }

    public Savings(@NotNull(message = "A balance is required")Money balance,
                   @NotNull(message = "A primaryOwner is required")AccountHolder primaryOwner,
                   AccountHolder secondaryOwner,
                   @NotNull(message = "A secretKey is required")String secretKey,
                   @DecimalMax(value = "1000", message = "The minimumBalance must be less than 1000")
                   @DecimalMin(value = "100", message = "The minimumBalance must be greater then 100")
                   BigDecimal minimumBalance, Date dateOfCreation) {

        super(balance, primaryOwner, secondaryOwner);
        this.lastInterestDate=new Date();
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
        this.minimumBalance = minimumBalance;

    }

    public Savings(@NotNull(message = "A balance is required")Money balance,
                   @NotNull(message = "A primaryOwner is required")AccountHolder primaryOwner,
                   AccountHolder secondaryOwner,
                   @NotNull(message = "A secretKey is required")String secretKey,
                   @DecimalMax(value = "1000", message = "The minimumBalance must be less than 1000")
                   @DecimalMin(value = "100", message = "The minimumBalance must be greater then 100") BigDecimal minimumBalance,
                   @DecimalMax(value = "0.5", message = "The interestRate can not be greater than 0.5")
                   @Positive(message = "The interestRate must be positive")BigDecimal interestRate
                   ) {
        super(balance, primaryOwner, secondaryOwner);
        this.lastInterestDate=new Date();
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;

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

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Date getLastInterestDate() {
        return lastInterestDate;
    }

    public void setLastInterestDate(Date lastInterestDate) {
        this.lastInterestDate = lastInterestDate;
    }
}

