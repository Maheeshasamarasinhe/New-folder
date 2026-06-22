package com.example.user_management_system.controller;

import com.example.user_management_system.dto.AddUsersToGroupDto;
import com.example.user_management_system.dto.CommonSearchDto;
import com.example.user_management_system.dto.GroupRequestDto;
import com.example.user_management_system.dto.GroupResponseDto;
import com.example.user_management_system.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<GroupResponseDto> createGroup(@Valid @RequestBody GroupRequestDto requestDto) {
        return new ResponseEntity<>(groupService.createGroup(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GroupResponseDto>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDto> getGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupResponseDto> updateGroup(@PathVariable Long id, @Valid @RequestBody GroupRequestDto requestDto) {
        return ResponseEntity.ok(groupService.updateGroup(id, requestDto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{groupId}/users/{userId}")
    public ResponseEntity<GroupResponseDto> addUserToGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        return ResponseEntity.ok(groupService.addUserToGroup(groupId, userId));
    }

    @DeleteMapping("/{groupId}/users/{userId}")
    public ResponseEntity<GroupResponseDto> removeUserFromGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        return ResponseEntity.ok(groupService.removeUserFromGroup(groupId, userId));
    }

    @PostMapping("/{groupId}/users")
    public ResponseEntity<GroupResponseDto> addUsersToGroup(@PathVariable Long groupId, @Valid @RequestBody AddUsersToGroupDto dto) {
        return ResponseEntity.ok(groupService.addUsersToGroup(groupId, dto.getUserIds()));
    }

    @PostMapping("/search")
    public ResponseEntity<List<GroupResponseDto>> searchGroups(@RequestBody CommonSearchDto searchDto) {
        return ResponseEntity.ok(groupService.searchGroups(searchDto.getSearchCriteria()));
    }


}
