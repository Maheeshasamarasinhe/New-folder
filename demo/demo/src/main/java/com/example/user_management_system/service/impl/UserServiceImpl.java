package com.example.user_management_system.service.impl;

import com.example.user_management_system.dto.UserRequestDto;
import com.example.user_management_system.dto.UserResponseDto;
import com.example.user_management_system.entity.User;
import com.example.user_management_system.exception.DataValidationException;
import com.example.user_management_system.exception.NotFoundException;
import com.example.user_management_system.mapper.UserMapper;
import com.example.user_management_system.repository.UserRepository;
import com.example.user_management_system.service.UserService;
import com.example.user_management_system.specification.Userspecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new NotFoundException("User with email " + dto.getEmail() + " already exists");
        }

        User user = userMapper.toEntity(dto);
        user.setStatus(true);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setUsername(dto.getUsername());
        existingUser.setEmail(dto.getEmail());
        existingUser.setPassword(dto.getPassword());
        existingUser.setRole(dto.getRole());
        existingUser.setTelephone(dto.getTelephone());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setStatus(false);
        userRepository.save(user);
    }

    @Override
    public List<UserResponseDto> searchUsers(Map<String, String> searchCriteria) {
        Userspecification specification = new Userspecification(searchCriteria);
        return userRepository.findAll(specification).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
