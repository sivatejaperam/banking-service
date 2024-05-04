package com.dbs.bankingservice.services;

import com.dbs.bankingservice.dto.*;
import com.dbs.bankingservice.entities.Transaction;
import com.dbs.bankingservice.entities.User;
import com.dbs.bankingservice.entities.UserAccount;
import com.dbs.bankingservice.enums.AccountType;
import com.dbs.bankingservice.enums.TransactionStatus;
import com.dbs.bankingservice.enums.TransactionType;
import com.dbs.bankingservice.exceptions.UserAccountNotFoundException;
import com.dbs.bankingservice.repositories.TransactionRepo;
import com.dbs.bankingservice.repositories.UserAccountRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserAccountService {

    UserAccountRepo userAccountRepo;
    TransactionRepo transactionRepo;
    UserService userService;

     public  List<UserAccountDto> getUserAccountDetails() {
        User user = userService.getLoggedInUser();
        List<UserAccount> userAccounts = userAccountRepo.findByUser(user);
        List<UserAccountDto> userAccountDtos = new ArrayList<>();
        userAccounts.stream().forEach(userAccount -> {
            UserAccountDto uad = new UserAccountDto();
            uad.setAccountType(userAccount.getAccountType());
            uad.setBalance(userAccount.getBalance());
            uad.setAccountNumber(userAccount.getAccountNumber());
            uad.setId(userAccount.getAccountId());
            userAccountDtos.add(uad);
        });

        return userAccountDtos;
    }

    public UserAccount getSavingsAccount() {
        User user = userService.getLoggedInUser();
        UserAccount savingsAccount = userAccountRepo.findByUserAndAccountType(user, AccountType.SAVINGS)
                .orElseThrow(() -> new UserAccountNotFoundException("User Does Not have any accounts"));
        return savingsAccount;
    }


    public TransactionsDto getUserTransactions(Long accountNumber,int pageNumber, int pageSize) {
        UserAccount userAccount = userAccountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount not found with account number " + accountNumber));
        if(pageNumber < 1) {
            pageNumber = 1;
        }
        if(pageSize < 1) {
            pageSize = 10;
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Transaction> transactionPage = transactionRepo.findByUserAccount(userAccount,pageable);
        List<TransactionDto> transactionDtos = new ArrayList<>();
        transactionPage.stream().forEach(transaction -> {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setId(transaction.getId());
            transactionDto.setComments(transaction.getComments());
            transactionDto.setTransactionType(transaction.getTransactionType());
            transactionDto.setAmount(transaction.getAmount());
            transactionDto.setTransactionDate(transaction.getTransactionDate());
            transactionDto.setTransactionStatus(transaction.getTransactionStatus());
            transactionDtos.add(transactionDto);
        });
        TransactionsDto transactionsDto = new TransactionsDto();
        transactionsDto.setTotalTransactions(transactionPage.getTotalElements());
        transactionsDto.setTransactions(transactionDtos);
        return transactionsDto;

    }

    @Transactional
    public String sendMoney(TransactionRequest transactionRequest) {
        Long toAccountNumber = transactionRequest.getToAccountNumber();
        Long amount = transactionRequest.getAmount();
        UserAccount toUserAccount = userAccountRepo.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount not found with account number " + toAccountNumber));
        UserAccount loginUserAccount = this.getSavingsAccount();
        if (loginUserAccount.getBalance() > amount) {
            loginUserAccount.setBalance(loginUserAccount.getBalance() - amount);
            saveTransaction(loginUserAccount, amount, TransactionType.DEBIT);

            toUserAccount.setBalance(toUserAccount.getBalance() + amount);
            saveTransaction(toUserAccount, amount, TransactionType.CREDIT);
            return "Successfully Send money";
        } else {
            return "In Sufficient balance";
        }
    }

    @Transactional
    protected void saveTransaction(UserAccount account, Long amount, TransactionType transactionType) {
        Transaction transaction = new Transaction();
        transaction.setUserAccount(account);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTransactionType(transactionType);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setAmount(amount);
        switch (transactionType) {
            case DEBIT -> transaction.setComments("Sent to " + account.getAccountNumber());
            case CREDIT -> transaction.setComments("Received from " + account.getAccountNumber());
            case WITHDRAW -> transaction.setComments("Withdrawn from " + account.getAccountNumber());
        }
        transactionRepo.save(transaction);
    }


    public void depositMoney(Long amount) {
        if (amount < 0) {
            throw new RuntimeException("Amount cannot be negative");
        }
        UserAccount loginUserAccount = this.getSavingsAccount();
        loginUserAccount.setBalance(loginUserAccount.getBalance() + amount);
    }
}
