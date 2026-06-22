package com.example.user_management_system.service;

import com.example.user_management_system.dto.GroupRequestDto;
import com.example.user_management_system.dto.GroupResponseDto;

import java.util.List;
import java.util.Map;

public interface GroupService {
    GroupResponseDto createGroup(GroupRequestDto dto);
    List<GroupResponseDto> getAllGroups();
    GroupResponseDto getGroupById(Long id);
    GroupResponseDto updateGroup(Long id, GroupRequestDto dto);
    void deleteGroup(Long id);
    GroupResponseDto addUserToGroup(Long groupId, Long userId);
    GroupResponseDto addUsersToGroup(Long groupId, List<Long> userIds);
    GroupResponseDto removeUserFromGroup(Long groupId, Long userId);
    List<GroupResponseDto> searchGroups(Map<String, String> searchCriteria);
}
