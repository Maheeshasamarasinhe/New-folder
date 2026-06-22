package com.example.user_management_system;

import com.example.user_management_system.dto.*;
import com.example.user_management_system.entity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockData {

    // ── User ──────────────────────────────────────────────────────────────────

    public static User mockUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("john_doe");
        user.setEmail("john@example.com");
        user.setPassword("Password@1");
        user.setRole("ADMIN");
        user.setTelephone("0712345678");
        user.setStatus(true);
        user.setBankAccounts(new ArrayList<>());
        return user;
    }

    public static UserRequestDto mockUserRequestDto() {
        UserRequestDto dto = new UserRequestDto();
        dto.setUsername("john_doe");
        dto.setEmail("john@example.com");
        dto.setPassword("Password@1");
        dto.setRole("ADMIN");
        dto.setTelephone("0712345678");
        return dto;
    }

    public static UserResponseDto mockUserResponseDto() {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(1L);
        dto.setUsername("john_doe");
        dto.setEmail("john@example.com");
        dto.setPassword("Password@1");
        dto.setRole("ADMIN");
        dto.setTelephone("0712345678");
        dto.setStatus(true);
        return dto;
    }

    // ── Group ─────────────────────────────────────────────────────────────────

    public static Group mockGroup() {
        Group group = new Group();
        group.setId(1L);
        group.setName("Developers");
        group.setDescription("Dev team");
        group.setUsers(new HashSet<>(Set.of(mockUser())));
        return group;
    }

    public static GroupRequestDto mockGroupRequestDto() {
        GroupRequestDto dto = new GroupRequestDto();
        dto.setName("Developers");
        dto.setDescription("Dev team");
        dto.setUserIds(List.of(1L));
        return dto;
    }

    public static GroupResponseDto mockGroupResponseDto() {
        GroupResponseDto dto = new GroupResponseDto();
        dto.setId(1L);
        dto.setName("Developers");
        dto.setDescription("Dev team");
        dto.setUsers(Set.of(mockUserResponseDto()));
        return dto;
    }

    // ── BankAccount ───────────────────────────────────────────────────────────

    public static BankAccount mockBankAccount() {
        BankAccount account = new BankAccount();
        account.setId(1L);
        account.setAccountNumber("ACC-001");
        account.setAccountHolderName("John Doe");
        account.setBankName("ABC Bank");
        account.setBranchCode("BR001");
        account.setBalance(5000.0);
        account.setStatus(true);
        account.setUser(mockUser());
        return account;
    }

    public static BankAccountRequestDto mockBankAccountRequestDto() {
        BankAccountRequestDto dto = new BankAccountRequestDto();
        dto.setAccountNumber("ACC-001");
        dto.setAccountHolderName("John Doe");
        dto.setBankName("ABC Bank");
        dto.setBranchCode("BR001");
        dto.setBalance(5000.0);
        dto.setUserId(1L);
        return dto;
    }

    public static BankAccountResponseDto mockBankAccountResponseDto() {
        BankAccountResponseDto dto = new BankAccountResponseDto();
        dto.setId(1L);
        dto.setAccountNumber("ACC-001");
        dto.setAccountHolderName("John Doe");
        dto.setBankName("ABC Bank");
        dto.setBranchCode("BR001");
        dto.setBalance(5000.0);
        dto.setUserId(1L);
        dto.setUsername("john_doe");
        return dto;
    }
}
