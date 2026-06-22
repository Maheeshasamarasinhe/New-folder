package com.example.user_management_system.service;

import com.example.user_management_system.dto.UserRequestDto;
import com.example.user_management_system.dto.UserResponseDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(Long id, UserRequestDto dto);
    void deleteUser(Long id);
    List<UserResponseDto> searchUsers(Map<String, String> searchCriteria);
}
