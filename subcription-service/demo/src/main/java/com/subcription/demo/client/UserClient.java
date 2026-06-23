package com.subcription.demo.client;

import com.subcription.demo.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/users/get/{id}")
    UserResponseDto getUserById(@PathVariable Long id);
}
