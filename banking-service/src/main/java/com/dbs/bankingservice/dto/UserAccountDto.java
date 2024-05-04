package com.dbs.bankingservice.dto;

import com.dbs.bankingservice.entities.Transaction;
import com.dbs.bankingservice.enums.AccountType;
import lombok.Data;

import java.util.List;

@Data
public class UserAccountDto {

    private Long id;
    private Long accountNumber;
    private Long balance;
    private AccountType accountType;

}
