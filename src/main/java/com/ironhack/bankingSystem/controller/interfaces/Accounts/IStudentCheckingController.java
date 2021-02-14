package com.ironhack.bankingSystem.controller.interfaces.Accounts;

import com.ironhack.bankingSystem.model.Account.Savings;
import com.ironhack.bankingSystem.model.Account.StudentChecking;

import java.util.Optional;

public interface IStudentCheckingController {

    public Optional<StudentChecking> findById(Integer id);
}
