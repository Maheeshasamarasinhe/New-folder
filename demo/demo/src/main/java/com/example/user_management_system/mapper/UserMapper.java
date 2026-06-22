package com.example.user_management_system.mapper;

import com.example.user_management_system.dto.UserRequestDto;
import com.example.user_management_system.dto.UserResponseDto;
import com.example.user_management_system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserRequestDto dto);
    UserResponseDto toDto(User user);
}
