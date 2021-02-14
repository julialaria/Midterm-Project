package com.ironhack.bankingSystem.controller.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ThirdPartyDTO {
    @NotNull(message = "A name is required")
    private String name;
    private final String hashedKey = String.valueOf(hashCode());

    public ThirdPartyDTO() {
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThirdPartyDTO that = (ThirdPartyDTO) o;
        return getName().equals(that.getName()) && getHashedKey().equals(that.getHashedKey());
    }

    public int hashCode() {
        return Objects.hash(getName());
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHashedKey() {
        return hashedKey;
    }
}