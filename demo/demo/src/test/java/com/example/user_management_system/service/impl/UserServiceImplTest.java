package com.example.user_management_system.service.impl;

import com.example.user_management_system.MockData;
import com.example.user_management_system.dto.UserRequestDto;
import com.example.user_management_system.dto.UserResponseDto;
import com.example.user_management_system.entity.User;
import com.example.user_management_system.exception.DuplicateAccountException;
import com.example.user_management_system.exception.NotFoundException;
import com.example.user_management_system.mapper.UserMapper;
import com.example.user_management_system.repository.UserRepository;
import com.example.user_management_system.specification.Userspecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock UserRepository userRepository;
    @Mock UserMapper userMapper;

    @InjectMocks UserServiceImpl userService;

    private User user;
    private UserRequestDto requestDto;
    private UserResponseDto responseDto;

    @BeforeEach
    void setUp() {
        user = MockData.mockUser();
        requestDto = MockData.mockUserRequestDto();
        responseDto = MockData.mockUserResponseDto();
    }

    @Test
    void createUser_success() {
        when(userRepository.existsByEmail(requestDto.getEmail())).thenReturn(false);
        when(userMapper.toEntity(requestDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.createUser(requestDto);

        assertThat(result.getEmail()).isEqualTo(requestDto.getEmail());
        verify(userRepository).save(user);
    }

    @Test
    void createUser_duplicateEmail_throwsDuplicateAccountException() {
        when(userRepository.existsByEmail(requestDto.getEmail())).thenReturn(true);

        assertThatThrownBy(() -> userService.createUser(requestDto))
                .isInstanceOf(DuplicateAccountException.class)
                .hasMessageContaining(requestDto.getEmail());

        verify(userRepository, never()).save(any());
    }

    @Test
    void getAllUsers_returnsList() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toDto(user)).thenReturn(responseDto);

        List<UserResponseDto> result = userService.getAllUsers();

        assertThat(result).hasSize(1);
    }

    @Test
    void getUserById_found() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.getUserById(1L);

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getUserById_notFound_throwsRuntimeException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(99L))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateUser_success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.updateUser(1L, requestDto);

        assertThat(result).isNotNull();
        verify(userRepository).save(user);
    }

    @Test
    void deleteUser_setsStatusFalse() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        assertThat(user.isStatus()).isFalse();
        verify(userRepository).save(user);
    }

    @Test
    void searchUsers_returnsList() {
        when(userRepository.findAll(any(Userspecification.class))).thenReturn(List.of(user));
        when(userMapper.toDto(user)).thenReturn(responseDto);

        List<UserResponseDto> result = userService.searchUsers(Map.of("username", "john_doe"));

        assertThat(result).hasSize(1);
    }
}
