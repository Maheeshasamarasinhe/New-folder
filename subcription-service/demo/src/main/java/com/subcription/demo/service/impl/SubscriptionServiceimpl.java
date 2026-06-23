package com.subcription.demo.service.impl;

import com.subcription.demo.client.UserClient;
import com.subcription.demo.dto.SubscriptionWithUserDto;
import com.subcription.demo.dto.UserResponseDto;
import com.subcription.demo.dto.subscriptionRequestDTO;
import com.subcription.demo.dto.subscriptionResponseDto;
import com.subcription.demo.entity.subscription;
import com.subcription.demo.exception.NotFoundException;
import com.subcription.demo.mapper.SubscriptionMapper;
import com.subcription.demo.repository.SubscriptionRepository;
import com.subcription.demo.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceimpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserClient userClient;

    @Override
    public subscriptionResponseDto createSubscription(subscriptionRequestDTO dto) {
        // Validate that the user exists before creating subscription
        userClient.getUserById(dto.getUserId());
        subscription entity = subscriptionMapper.toEntity(dto);
        return subscriptionMapper.toDto(subscriptionRepository.save(entity));
    }

    @Override
    public List<subscriptionResponseDto> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(subscriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public subscriptionResponseDto getSubscriptionById(Long id) {
        subscription sub = subscriptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subscription not found with id: " + id));
        return subscriptionMapper.toDto(sub);
    }

    @Override
    public subscriptionResponseDto updateSubscription(Long id, subscriptionRequestDTO dto) {
        subscription existing = subscriptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subscription not found with id: " + id));
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setUserId(dto.getUserId());
        return subscriptionMapper.toDto(subscriptionRepository.save(existing));
    }

    @Override
    public void deleteSubscripption(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new NotFoundException("Subscription not found with id: " + id);
        }
        subscriptionRepository.deleteById(id);
    }

    @Override
    public List<SubscriptionWithUserDto> getSubscriptionsByUserId(Long userId) {
        UserResponseDto user = userClient.getUserById(userId);
        return subscriptionRepository.findByUserId(userId).stream()
                .map(sub -> {
                    SubscriptionWithUserDto dto = new SubscriptionWithUserDto();
                    dto.setId(sub.getId());
                    dto.setName(sub.getName());
                    dto.setDescription(sub.getDescription());
                    dto.setPrice(sub.getPrice());
                    dto.setUser(user);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
