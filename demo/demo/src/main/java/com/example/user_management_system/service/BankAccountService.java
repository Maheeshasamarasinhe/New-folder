package com.example.user_management_system.service;

import com.example.user_management_system.dto.BankAccountRequestDto;
import com.example.user_management_system.dto.BankAccountResponseDto;

import java.util.List;

public interface BankAccountService {
    BankAccountResponseDto createBankAccount(BankAccountRequestDto dto);
    List<BankAccountResponseDto> getAllBankAccounts();
    BankAccountResponseDto getBankAccountById(Long id);
    BankAccountResponseDto updateBankAccount(Long id, BankAccountRequestDto dto);
    void deleteBankAccount(Long id);
}
