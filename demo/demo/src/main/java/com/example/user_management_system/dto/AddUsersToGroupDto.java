package com.example.user_management_system.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class AddUsersToGroupDto {

    @NotEmpty(message = "group name cannot be empty")
    private String groupName;

    @NotEmpty(message = "User IDs cannot be empty")
    private List<Long> userIds;
}
