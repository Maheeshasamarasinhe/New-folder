package com.example.user_management_system.service.impl;

import com.example.user_management_system.dto.GroupRequestDto;
import com.example.user_management_system.dto.GroupResponseDto;
import com.example.user_management_system.entity.Group;
import com.example.user_management_system.entity.User;
import com.example.user_management_system.mapper.GroupMapper;
import com.example.user_management_system.repository.GroupRepository;
import com.example.user_management_system.repository.UserRepository;
import com.example.user_management_system.service.GroupService;
import com.example.user_management_system.specification.GroupSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupMapper groupMapper;

    @Override
    public GroupResponseDto createGroup(GroupRequestDto dto) {
        Group group = groupMapper.toEntity(dto);
        
        if (dto.getUserIds() != null && !dto.getUserIds().isEmpty()) {
            List<User> users = userRepository.findAllById(dto.getUserIds());
            if (users.size() != dto.getUserIds().size()) {
                throw new RuntimeException("Some users not found");
            }
            group.getUsers().addAll(users);
        }
        
        Group savedGroup = groupRepository.save(group);
        return groupMapper.toDto(savedGroup);
    }

    @Override
    public List<GroupResponseDto> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(groupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupResponseDto getGroupById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        return groupMapper.toDto(group);
    }

    @Override
    public GroupResponseDto updateGroup(Long id, GroupRequestDto dto) {
        Group existingGroup = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        
        existingGroup.setName(dto.getName());
        existingGroup.setDescription(dto.getDescription());
        
        Group updatedGroup = groupRepository.save(existingGroup);
        return groupMapper.toDto(updatedGroup);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public GroupResponseDto addUserToGroup(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + groupId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        group.getUsers().add(user);
        Group updatedGroup = groupRepository.save(group);
        return groupMapper.toDto(updatedGroup);
    }

    @Override
    public GroupResponseDto addUsersToGroup(Long groupId, List<Long> userIds) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + groupId));
        
        List<User> users = userRepository.findAllById(userIds);
        if (users.size() != userIds.size()) {
            throw new RuntimeException("Some users not found");
        }
        
        group.getUsers().addAll(users);
        Group updatedGroup = groupRepository.save(group);
        return groupMapper.toDto(updatedGroup);
    }

    @Override
    public GroupResponseDto removeUserFromGroup(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + groupId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        group.getUsers().remove(user);
        Group updatedGroup = groupRepository.save(group);
        return groupMapper.toDto(updatedGroup);
    }

    @Override
    public List<GroupResponseDto> searchGroups(Map<String, String> searchCriteria) {
        GroupSpecification specification = new GroupSpecification(searchCriteria);
        return groupRepository.findAll(specification).stream()
                .map(groupMapper::toDto)
                .collect(Collectors.toList());
    }
}
