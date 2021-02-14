package com.ironhack.bankingSystem.service.impl.Accounts;

import com.ironhack.bankingSystem.repository.Accounts.StudentCheckingRepository;
import com.ironhack.bankingSystem.service.interfaces.Accounts.IStudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentCheckingService implements IStudentCheckingService {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

}
