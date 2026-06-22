package com.example.user_management_system.mapper;

import com.example.user_management_system.dto.UserRequestDto;
import com.example.user_management_system.dto.UserResponseDto;
import com.example.user_management_system.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-18T11:13:48+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( dto.getUsername() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );
        user.setRole( dto.getRole() );
        user.setTelephone( dto.getTelephone() );

        return user;
    }

    @Override
    public UserResponseDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setUsername( user.getUsername() );
        userResponseDto.setPassword( user.getPassword() );
        userResponseDto.setEmail( user.getEmail() );
        userResponseDto.setRole( user.getRole() );
        userResponseDto.setTelephone( user.getTelephone() );
        userResponseDto.setStatus( user.isStatus() );

        return userResponseDto;
    }
}
