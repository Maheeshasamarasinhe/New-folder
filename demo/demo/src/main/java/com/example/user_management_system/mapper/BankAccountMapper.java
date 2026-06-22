package com.example.user_management_system.mapper;

import com.example.user_management_system.dto.BankAccountRequestDto;
import com.example.user_management_system.dto.BankAccountResponseDto;
import com.example.user_management_system.entity.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BankAccountMapper {
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    BankAccount toEntity(BankAccountRequestDto dto);
    
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    BankAccountResponseDto toDto(BankAccount bankAccount);
}
