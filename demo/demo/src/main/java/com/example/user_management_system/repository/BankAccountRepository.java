package com.example.user_management_system.repository;

import com.example.user_management_system.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query("SELECT b.accountNumber FROM BankAccount b where b.status=true ")
    List<String> findAllAccountNumbers();


}
