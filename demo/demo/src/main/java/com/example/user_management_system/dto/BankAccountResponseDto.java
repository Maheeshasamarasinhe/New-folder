package com.example.user_management_system.dto;

import lombok.Data;

@Data
public class BankAccountResponseDto {

    private Long id;

    private String accountNumber;
    private String accountHolderName;
    private String bankName;
    private String branchCode;
    private Double balance;
    private Long userId;
    private String username;
}
