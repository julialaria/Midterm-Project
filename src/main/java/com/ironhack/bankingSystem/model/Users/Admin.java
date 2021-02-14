package com.ironhack.bankingSystem.model.Users;

import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.*;
@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class Admin extends User{

    public Admin (){
    }
    public Admin(String name, String username, String password) {
        super(name, username, password);
    }


}
