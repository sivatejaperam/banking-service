package com.dbs.bankingservice.repositories;

import com.dbs.bankingservice.entities.Transaction;
import com.dbs.bankingservice.entities.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByUserAccount(UserAccount userAccount, Pageable pageable);
}
