package com.ironhack.bankingSystem.controller.impl.Accounts;

import com.ironhack.bankingSystem.controller.interfaces.Accounts.IStudentCheckingController;
import com.ironhack.bankingSystem.model.Account.Savings;
import com.ironhack.bankingSystem.model.Account.StudentChecking;
import com.ironhack.bankingSystem.repository.Accounts.StudentCheckingRepository;
import com.ironhack.bankingSystem.service.interfaces.Accounts.IStudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentCheckingController implements IStudentCheckingController {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    IStudentCheckingService studentCheckingService;

    @GetMapping("/studentChecking")
    public List<StudentChecking> findAllStudentChecking() {
        return studentCheckingRepository.findAll();
    }

    @GetMapping("/studentChecking/by-id/{id}")
    public Optional<StudentChecking> findById(@PathVariable Integer id) {
        return studentCheckingRepository.findById(id);
    }


}
