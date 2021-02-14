package com.ironhack.bankingSystem.controller.dto;

import com.ironhack.bankingSystem.model.others.Address;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class AccountHolderDTO {
    @NotNull(message = "A name is required")
    private String name;
    @NotNull(message = "A date of birth is required")
    private Date dateOfBirth;
    @Embedded
    @NotNull(message = "A primary address is required")
    private Address primaryAddress ;
    @Embedded
    private Address mailingAddress;
    @NotNull(message = "An username is required")
    private String username;
    @NotNull(message = "A password is required")
    private String password;

    public AccountHolderDTO() {
    }

    public AccountHolderDTO(@NotNull(message = "Name can not be empty") String name,
                            @NotNull(message = "Date of birth can not be empty") Date dateOfBirth,
                            @NotNull (message = "A primary address is required") Address primaryAddress,
                            @NotNull(message = "Username can not be empty") String username,
                            @NotNull(message = "Password can not be empty") String password) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.username = username;
        this.password = password;
    }

    public AccountHolderDTO(@NotNull(message = "Name can not be empty") String name,
                            @NotNull(message = "Date of birth can not be empty") Date dateOfBirth,
                            @NotNull (message = "A primary adress is required") Address primaryAddress,
                            Address mailingAddress,
                            @NotNull(message = "Username can not be empty") String username,
                            @NotNull(message = "Password can not be empty") String password) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.username = username;
        this.password = password;
        this.mailingAddress = mailingAddress;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Address getPrimaryAddress() {
        return primaryAddress;
    }
    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Address getMailingAddress() {
        return mailingAddress;
    }
    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
