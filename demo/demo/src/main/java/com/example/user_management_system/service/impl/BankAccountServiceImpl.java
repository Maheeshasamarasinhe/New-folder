package com.example.user_management_system.service.impl;

import com.example.user_management_system.dto.BankAccountRequestDto;
import com.example.user_management_system.dto.BankAccountResponseDto;
import com.example.user_management_system.entity.BankAccount;
import com.example.user_management_system.entity.User;
import com.example.user_management_system.exception.DuplicateAccountException;
import com.example.user_management_system.exception.NotFoundException;
import com.example.user_management_system.mapper.BankAccountMapper;
import com.example.user_management_system.repository.BankAccountRepository;
import com.example.user_management_system.repository.UserRepository;
import com.example.user_management_system.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;
    private final BankAccountMapper bankAccountMapper;

    @Override
    public BankAccountResponseDto createBankAccount(BankAccountRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        String newAccountNumber = dto.getAccountNumber();
        List<String> allAccountNumbers = bankAccountRepository.findAllAccountNumbers();

        boolean duplicate = allAccountNumbers.stream()
                .anyMatch(accountNumber -> accountNumber.equals(newAccountNumber));

        if (duplicate) {
            throw new DuplicateAccountException("Bank account with account number " + newAccountNumber + " already exists");
        }




        BankAccount bankAccount = bankAccountMapper.toEntity(dto);
        bankAccount.setStatus(true);
        bankAccount.setUser(user);
        
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return bankAccountMapper.toDto(savedBankAccount);
    }

    @Override
    public List<BankAccountResponseDto> getAllBankAccounts() {
        return bankAccountRepository.findAll().stream()
                .map(bankAccountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountResponseDto getBankAccountById(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank account not found with id: " + id));
        return bankAccountMapper.toDto(bankAccount);
    }

    @Override
    public BankAccountResponseDto updateBankAccount(Long id, BankAccountRequestDto dto) {
        BankAccount existingBankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank account not found with id: " + id));
        
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        existingBankAccount.setAccountNumber(dto.getAccountNumber());
        existingBankAccount.setAccountHolderName(dto.getAccountHolderName());
        existingBankAccount.setBankName(dto.getBankName());
        existingBankAccount.setBranchCode(dto.getBranchCode());
        existingBankAccount.setBalance(dto.getBalance());
        existingBankAccount.setUser(user);

        BankAccount updatedBankAccount = bankAccountRepository.save(existingBankAccount);
        return bankAccountMapper.toDto(updatedBankAccount);
    }

    @Override
    public void deleteBankAccount(Long id) {
       BankAccount bankAccount = bankAccountRepository.findById(id)
                         .orElseThrow(() -> new RuntimeException("bankAccount  not found with id: " + id));
        bankAccount.setStatus(false);


        bankAccountRepository.save(bankAccount);
    }
}
