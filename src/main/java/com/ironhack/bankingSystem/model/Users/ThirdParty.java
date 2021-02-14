package com.ironhack.bankingSystem.model.Users;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ThirdParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @NotNull (message = "A name is required")
    private String name;
    @NotNull (message = "A hashedKey is required")
    private String hashedKey;

    public ThirdParty(){

    }

    public ThirdParty(@NotNull(message = "A name is required") String name, @NotNull(message = "A hashedKey is required") String hashedKey) {
        this.name = name;
        this.hashedKey = hashedKey;
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

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public Integer getId() {
        return id;
    }
}
