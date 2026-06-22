package com.example.user_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BankAccountRequestDto {

    @NotBlank(message = "Account number is required")
    private String accountNumber;
    
    @NotBlank(message = "Account holder name is required")
    private String accountHolderName;
    
    @NotBlank(message = "Bank name is required")
    private String bankName;
    
    private String branchCode;
    
    @NotNull(message = "Balance is required")
    private Double balance;
    
    @NotNull(message = "User ID is required")
    private Long userId;
}
