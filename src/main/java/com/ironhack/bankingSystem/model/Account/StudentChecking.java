package com.ironhack.bankingSystem.model.Account;

import com.ironhack.bankingSystem.enums.Status;
import com.ironhack.bankingSystem.model.Users.AccountHolder;
import com.ironhack.bankingSystem.model.others.Money;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class StudentChecking extends Account {
    @NotNull
    String secretKey;
    @Enumerated(EnumType.STRING)
    Status status;

    public StudentChecking(){
    }

    public StudentChecking(@NotNull(message = "A balance is required") Money balance,
                           @NotNull(message = "A primaryOwner is required") AccountHolder primaryOwner,
                           AccountHolder secondaryOwner,
                           @NotNull(message = "A secretKey is required") String secretKey) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
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
}
