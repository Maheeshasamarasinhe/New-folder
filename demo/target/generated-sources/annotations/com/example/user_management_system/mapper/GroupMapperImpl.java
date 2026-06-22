package com.example.user_management_system.mapper;

import com.example.user_management_system.dto.GroupRequestDto;
import com.example.user_management_system.dto.GroupResponseDto;
import com.example.user_management_system.dto.UserResponseDto;
import com.example.user_management_system.entity.Group;
import com.example.user_management_system.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-18T11:13:49+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class GroupMapperImpl implements GroupMapper {

    @Override
    public Group toEntity(GroupRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Group group = new Group();

        group.setName( dto.getName() );
        group.setDescription( dto.getDescription() );

        return group;
    }

    @Override
    public GroupResponseDto toDto(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupResponseDto groupResponseDto = new GroupResponseDto();

        groupResponseDto.setId( group.getId() );
        groupResponseDto.setName( group.getName() );
        groupResponseDto.setDescription( group.getDescription() );
        groupResponseDto.setUsers( userSetToUserResponseDtoSet( group.getUsers() ) );

        return groupResponseDto;
    }

    protected UserResponseDto userToUserResponseDto(User user) {
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

    protected Set<UserResponseDto> userSetToUserResponseDtoSet(Set<User> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserResponseDto> set1 = new LinkedHashSet<UserResponseDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( User user : set ) {
            set1.add( userToUserResponseDto( user ) );
        }

        return set1;
    }
}
