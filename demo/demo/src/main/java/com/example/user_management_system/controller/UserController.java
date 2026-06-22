package com.example.user_management_system.controller;

import com.example.user_management_system.dto.CommonSearchDto;
import com.example.user_management_system.dto.UserRequestDto;
import com.example.user_management_system.dto.UserResponseDto;
import com.example.user_management_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto requestDto) {
        return new ResponseEntity<>(userService.createUser(requestDto), HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("get/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }


    @PutMapping("put/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.updateUser(id, requestDto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserResponseDto>> searchUsers(@RequestBody CommonSearchDto searchDto) {
        return ResponseEntity.ok(userService.searchUsers(searchDto.getSearchCriteria()));
    }
}