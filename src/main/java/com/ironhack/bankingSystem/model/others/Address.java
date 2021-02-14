package com.ironhack.bankingSystem.model.others;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import javax.validation.constraints.*;

@Embeddable
public class Address {
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private Integer zipCode;
    public Address() {
    }
    public Address(@NotNull String street, @NotNull String city, @NotNull String country, @NotNull Integer zipCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Integer getZipCode() {
        return zipCode;
    }
    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}