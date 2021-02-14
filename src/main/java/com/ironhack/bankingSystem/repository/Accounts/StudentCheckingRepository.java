package com.ironhack.bankingSystem.repository.Accounts;

import com.ironhack.bankingSystem.model.Account.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Integer> {
}
