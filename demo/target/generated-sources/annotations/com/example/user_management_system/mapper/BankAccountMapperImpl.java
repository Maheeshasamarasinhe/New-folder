package com.example.user_management_system.mapper;

import com.example.user_management_system.dto.BankAccountRequestDto;
import com.example.user_management_system.dto.BankAccountResponseDto;
import com.example.user_management_system.entity.BankAccount;
import com.example.user_management_system.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-18T11:13:49+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class BankAccountMapperImpl implements BankAccountMapper {

    @Override
    public BankAccount toEntity(BankAccountRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        BankAccount bankAccount = new BankAccount();

        bankAccount.setAccountNumber( dto.getAccountNumber() );
        bankAccount.setAccountHolderName( dto.getAccountHolderName() );
        bankAccount.setBankName( dto.getBankName() );
        bankAccount.setBranchCode( dto.getBranchCode() );
        bankAccount.setBalance( dto.getBalance() );

        return bankAccount;
    }

    @Override
    public BankAccountResponseDto toDto(BankAccount bankAccount) {
        if ( bankAccount == null ) {
            return null;
        }

        BankAccountResponseDto bankAccountResponseDto = new BankAccountResponseDto();

        bankAccountResponseDto.setUserId( bankAccountUserId( bankAccount ) );
        bankAccountResponseDto.setUsername( bankAccountUserUsername( bankAccount ) );
        bankAccountResponseDto.setId( bankAccount.getId() );
        bankAccountResponseDto.setAccountNumber( bankAccount.getAccountNumber() );
        bankAccountResponseDto.setAccountHolderName( bankAccount.getAccountHolderName() );
        bankAccountResponseDto.setBankName( bankAccount.getBankName() );
        bankAccountResponseDto.setBranchCode( bankAccount.getBranchCode() );
        bankAccountResponseDto.setBalance( bankAccount.getBalance() );

        return bankAccountResponseDto;
    }

    private Long bankAccountUserId(BankAccount bankAccount) {
        if ( bankAccount == null ) {
            return null;
        }
        User user = bankAccount.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String bankAccountUserUsername(BankAccount bankAccount) {
        if ( bankAccount == null ) {
            return null;
        }
        User user = bankAccount.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
