package com.dbs.bankingservice.repositories;

import com.dbs.bankingservice.entities.User;
import com.dbs.bankingservice.entities.UserAccount;
import com.dbs.bankingservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepo extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUserAndAccountType(User user, AccountType accountType);
    List<UserAccount> findByUser(User user);
    Optional<UserAccount> findByAccountNumber(Long accountNumber);
}
