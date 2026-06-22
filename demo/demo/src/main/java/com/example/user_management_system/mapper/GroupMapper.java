package com.example.user_management_system.mapper;

import com.example.user_management_system.dto.GroupRequestDto;
import com.example.user_management_system.dto.GroupResponseDto;
import com.example.user_management_system.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {
    Group toEntity(GroupRequestDto dto);
    GroupResponseDto toDto(Group group);
}
