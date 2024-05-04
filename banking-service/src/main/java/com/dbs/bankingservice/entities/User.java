package com.dbs.bankingservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAccount> accounts = new ArrayList<>();

    public void addAccount(UserAccount account) {
        this.accounts.add(account);
        account.setUser(this);
    }

    public void removeAccount(UserAccount account) {
        this.accounts.remove(account);
        account.setUser(null);
    }

}
