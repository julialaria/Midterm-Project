package com.ironhack.bankingSystem.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.bankingSystem.model.Account.Account;
import com.ironhack.bankingSystem.model.others.Address;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;
@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class AccountHolder extends User {

    @NotNull(message = "A date of birth is required")
    private Date dateOfBirth;
    @Embedded
    @NotNull(message = "A primary Address is required")
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street", column=@Column(name="mailing_street")),
            @AttributeOverride(name="city", column=@Column(name="mailing_city")),
            @AttributeOverride(name="country", column=@Column(name="mailing_country")),
            @AttributeOverride(name="zipCode", column=@Column(name="mailing_zip"))
    })
    private Address mailingAddress;

    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> primaryAccounts = new ArrayList<>();
    @OneToMany(mappedBy = "secondaryOwner")
    @JsonIgnore
    private List<Account> secondaryAccounts = new ArrayList<>();

    public AccountHolder() {
    }
    public AccountHolder(@NotNull(message = "A name is required") String name,
                         @NotNull(message = "An username is required") String username,
                         @NotNull(message = "A password is required") String password,
                         @NotNull(message = "A birth date is required") Date dateOfBirth,
                         @NotNull(message = "A primary Address is required") Address primaryAddress) {
        super(name, username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }
    public AccountHolder(@NotNull(message = "A name is required") String name,
                         @NotNull(message = "An username is required") String username,
                         @NotNull(message = "A password is required") String password,
                         @NotNull(message = "A birth date is required") Date dateOfBirth,
                         @NotNull(message = "A primary Address is required") Address primaryAddressAddress,
                         Address mailingAddress) {
        super(name, username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
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
    public List<Account> getPrimaryAccounts() {
        return primaryAccounts;
    }
    public void setPrimaryAccounts(List<Account> primaryAccounts) {
        this.primaryAccounts = primaryAccounts;
    }
    public List<Account> getSecondaryAccounts() {
        return secondaryAccounts;
    }
    public void setSecondaryAccounts(List<Account> secondaryAccounts) {
        this.secondaryAccounts = secondaryAccounts;
    }
    public Address getMailingAddress() {
        return mailingAddress;
    }
    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}