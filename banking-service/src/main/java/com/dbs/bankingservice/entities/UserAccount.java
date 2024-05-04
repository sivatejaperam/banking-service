package com.dbs.bankingservice.entities;

import com.dbs.bankingservice.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER_ACCOUNTS")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountId;

    @Column(name = "account_number",unique = true)
    private Long accountNumber ;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setUserAccount(this);
    }

    public void removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setUserAccount(null);
    }
}
