package com.subcription.demo.service;

import com.subcription.demo.dto.SubscriptionWithUserDto;
import com.subcription.demo.dto.subscriptionRequestDTO;
import com.subcription.demo.dto.subscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {
    subscriptionResponseDto createSubscription(subscriptionRequestDTO dto);
    List<subscriptionResponseDto> getAllSubscriptions();
    subscriptionResponseDto getSubscriptionById(Long id);
    subscriptionResponseDto updateSubscription(Long id, subscriptionRequestDTO requestDTO);
    void deleteSubscripption(Long id);
    List<SubscriptionWithUserDto> getSubscriptionsByUserId(Long userId);
}
