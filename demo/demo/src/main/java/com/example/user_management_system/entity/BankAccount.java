package com.example.user_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bank_accounts")
@Data
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String accountNumber;
    private String accountHolderName;
    private String bankName;
    private String branchCode;
    private Double balance;
    private boolean status= true;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
