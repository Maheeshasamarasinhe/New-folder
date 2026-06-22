package com.example.user_management_system.service.impl;

import com.example.user_management_system.MockData;
import com.example.user_management_system.dto.BankAccountRequestDto;
import com.example.user_management_system.dto.BankAccountResponseDto;
import com.example.user_management_system.entity.BankAccount;
import com.example.user_management_system.entity.User;
import com.example.user_management_system.exception.DuplicateAccountException;
import com.example.user_management_system.mapper.BankAccountMapper;
import com.example.user_management_system.repository.BankAccountRepository;
import com.example.user_management_system.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplTest {

    @Mock BankAccountRepository bankAccountRepository;
    @Mock UserRepository userRepository;
    @Mock BankAccountMapper bankAccountMapper;

    @InjectMocks BankAccountServiceImpl bankAccountService;

    private BankAccount bankAccount;
    private BankAccountRequestDto requestDto;
    private BankAccountResponseDto responseDto;
    private User user;

    @BeforeEach
    void setUp() {
        user = MockData.mockUser();
        bankAccount = MockData.mockBankAccount();
        requestDto = MockData.mockBankAccountRequestDto();
        responseDto = MockData.mockBankAccountResponseDto();
    }

    @Test
    void createBankAccount_success() {
        when(userRepository.findById(requestDto.getUserId())).thenReturn(Optional.of(user));
        when(bankAccountRepository.findAllAccountNumbers()).thenReturn(List.of());
        when(bankAccountMapper.toEntity(requestDto)).thenReturn(bankAccount);
        when(bankAccountRepository.save(bankAccount)).thenReturn(bankAccount);
        when(bankAccountMapper.toDto(bankAccount)).thenReturn(responseDto);

        BankAccountResponseDto result = bankAccountService.createBankAccount(requestDto);

        assertThat(result.getAccountNumber()).isEqualTo("ACC-001");
        verify(bankAccountRepository).save(bankAccount);
    }

    @Test
    void createBankAccount_duplicateAccount_throwsDuplicateAccountException() {
        when(userRepository.findById(requestDto.getUserId())).thenReturn(Optional.of(user));
        when(bankAccountRepository.findAllAccountNumbers()).thenReturn(List.of("ACC-001"));

        assertThatThrownBy(() -> bankAccountService.createBankAccount(requestDto))
                .isInstanceOf(DuplicateAccountException.class);

        verify(bankAccountRepository, never()).save(any());
    }

    @Test
    void createBankAccount_userNotFound_throwsRuntimeException() {
        when(userRepository.findById(requestDto.getUserId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bankAccountService.createBankAccount(requestDto))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void getAllBankAccounts_returnsList() {
        when(bankAccountRepository.findAll()).thenReturn(List.of(bankAccount));
        when(bankAccountMapper.toDto(bankAccount)).thenReturn(responseDto);

        List<BankAccountResponseDto> result = bankAccountService.getAllBankAccounts();

        assertThat(result).hasSize(1);
    }

    @Test
    void getBankAccountById_found() {
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(bankAccount));
        when(bankAccountMapper.toDto(bankAccount)).thenReturn(responseDto);

        BankAccountResponseDto result = bankAccountService.getBankAccountById(1L);

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getBankAccountById_notFound_throwsRuntimeException() {
        when(bankAccountRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bankAccountService.getBankAccountById(99L))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateBankAccount_success() {
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(bankAccount));
        when(userRepository.findById(requestDto.getUserId())).thenReturn(Optional.of(user));
        when(bankAccountRepository.save(bankAccount)).thenReturn(bankAccount);
        when(bankAccountMapper.toDto(bankAccount)).thenReturn(responseDto);

        BankAccountResponseDto result = bankAccountService.updateBankAccount(1L, requestDto);

        assertThat(result).isNotNull();
        verify(bankAccountRepository).save(bankAccount);
    }

    @Test
    void deleteBankAccount_setsStatusFalse() {
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(bankAccount));

        bankAccountService.deleteBankAccount(1L);

        assertThat(bankAccount.isStatus()).isFalse();
        verify(bankAccountRepository).save(bankAccount);
    }

    @Test
    void deleteBankAccount_notFound_throwsRuntimeException() {
        when(bankAccountRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bankAccountService.deleteBankAccount(99L))
                .isInstanceOf(RuntimeException.class);
    }
}
