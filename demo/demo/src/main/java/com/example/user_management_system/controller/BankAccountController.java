package com.example.user_management_system.controller;

import com.example.user_management_system.dto.BankAccountRequestDto;
import com.example.user_management_system.dto.BankAccountResponseDto;
import com.example.user_management_system.service.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/bank-accounts")
@RequiredArgsConstructor

public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping("/create")
    public ResponseEntity<BankAccountResponseDto> createBankAccount(@Valid @RequestBody BankAccountRequestDto requestDto) {
        return new ResponseEntity<>(bankAccountService.createBankAccount(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BankAccountResponseDto>> getAllBankAccounts() {
        return ResponseEntity.ok(bankAccountService.getAllBankAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponseDto> getBankAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(bankAccountService.getBankAccountById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountResponseDto> updateBankAccount(@PathVariable Long id, @Valid @RequestBody BankAccountRequestDto requestDto) {
        return ResponseEntity.ok(bankAccountService.updateBankAccount(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
        return ResponseEntity.noContent().build();
    }
}
