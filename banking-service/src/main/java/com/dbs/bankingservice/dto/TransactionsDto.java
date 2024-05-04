package com.dbs.bankingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionsDto {
    private List<TransactionDto> transactions;
    private long totalTransactions;
}
