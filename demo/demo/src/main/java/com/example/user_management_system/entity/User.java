package com.example.user_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    private String telephone;
    @Column(nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts;
}

