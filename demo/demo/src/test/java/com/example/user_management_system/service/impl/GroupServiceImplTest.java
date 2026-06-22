package com.example.user_management_system.service.impl;

import com.example.user_management_system.MockData;
import com.example.user_management_system.dto.GroupRequestDto;
import com.example.user_management_system.dto.GroupResponseDto;
import com.example.user_management_system.entity.Group;
import com.example.user_management_system.entity.User;
import com.example.user_management_system.mapper.GroupMapper;
import com.example.user_management_system.repository.GroupRepository;
import com.example.user_management_system.repository.UserRepository;
import com.example.user_management_system.specification.GroupSpecification;
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
class GroupServiceImplTest {

    @Mock GroupRepository groupRepository;
    @Mock UserRepository userRepository;
    @Mock GroupMapper groupMapper;

    @InjectMocks GroupServiceImpl groupService;

    private Group group;
    private GroupRequestDto requestDto;
    private GroupResponseDto responseDto;
    private User user;

    @BeforeEach
    void setUp() {
        user = MockData.mockUser();
        group = MockData.mockGroup();
        requestDto = MockData.mockGroupRequestDto();
        responseDto = MockData.mockGroupResponseDto();
    }

    @Test
    void createGroup_success() {
        when(groupMapper.toEntity(requestDto)).thenReturn(group);
        when(userRepository.findAllById(requestDto.getUserIds())).thenReturn(List.of(user));
        when(groupRepository.save(group)).thenReturn(group);
        when(groupMapper.toDto(group)).thenReturn(responseDto);

        GroupResponseDto result = groupService.createGroup(requestDto);

        assertThat(result.getName()).isEqualTo("Developers");
        verify(groupRepository).save(group);
    }

    @Test
    void createGroup_userNotFound_throwsRuntimeException() {
        when(groupMapper.toEntity(requestDto)).thenReturn(group);
        when(userRepository.findAllById(requestDto.getUserIds())).thenReturn(List.of()); // mismatch

        assertThatThrownBy(() -> groupService.createGroup(requestDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Some users not found");
    }

    @Test
    void getAllGroups_returnsList() {
        when(groupRepository.findAll()).thenReturn(List.of(group));
        when(groupMapper.toDto(group)).thenReturn(responseDto);

        List<GroupResponseDto> result = groupService.getAllGroups();

        assertThat(result).hasSize(1);
    }

    @Test
    void getGroupById_found() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(groupMapper.toDto(group)).thenReturn(responseDto);

        GroupResponseDto result = groupService.getGroupById(1L);

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getGroupById_notFound_throwsRuntimeException() {
        when(groupRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> groupService.getGroupById(99L))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateGroup_success() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(groupRepository.save(group)).thenReturn(group);
        when(groupMapper.toDto(group)).thenReturn(responseDto);

        GroupResponseDto result = groupService.updateGroup(1L, requestDto);

        assertThat(result).isNotNull();
        verify(groupRepository).save(group);
    }

    @Test
    void deleteGroup_callsRepository() {
        doNothing().when(groupRepository).deleteById(1L);

        groupService.deleteGroup(1L);

        verify(groupRepository).deleteById(1L);
    }

    @Test
    void addUserToGroup_success() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(groupRepository.save(group)).thenReturn(group);
        when(groupMapper.toDto(group)).thenReturn(responseDto);

        GroupResponseDto result = groupService.addUserToGroup(1L, 1L);

        assertThat(result).isNotNull();
    }

    @Test
    void addUsersToGroup_success() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(userRepository.findAllById(List.of(1L))).thenReturn(List.of(user));
        when(groupRepository.save(group)).thenReturn(group);
        when(groupMapper.toDto(group)).thenReturn(responseDto);

        GroupResponseDto result = groupService.addUsersToGroup(1L, List.of(1L));

        assertThat(result).isNotNull();
    }

    @Test
    void removeUserFromGroup_success() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(groupRepository.save(group)).thenReturn(group);
        when(groupMapper.toDto(group)).thenReturn(responseDto);

        GroupResponseDto result = groupService.removeUserFromGroup(1L, 1L);

        assertThat(result).isNotNull();
    }

    @Test
    void searchGroups_returnsList() {
        when(groupRepository.findAll(any(GroupSpecification.class))).thenReturn(List.of(group));
        when(groupMapper.toDto(group)).thenReturn(responseDto);

        List<GroupResponseDto> result = groupService.searchGroups(Map.of("name", "Developers"));

        assertThat(result).hasSize(1);
    }
}
