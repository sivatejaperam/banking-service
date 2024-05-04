package com.dbs.bankingservice.dto;

import com.dbs.bankingservice.enums.TransactionStatus;
import com.dbs.bankingservice.enums.TransactionType;
import jakarta.persistence.*;

import java.util.Date;

public class UserTransactionDto {

    private Integer amount;
    private Date transactionDate;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
}
