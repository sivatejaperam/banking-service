package com.dbs.bankingservice.dto;

import com.dbs.bankingservice.entities.UserAccount;
import com.dbs.bankingservice.enums.TransactionStatus;
import com.dbs.bankingservice.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto {

    private Long id ;
    private Long amount;
    private LocalDateTime transactionDate;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private String comments;
}
