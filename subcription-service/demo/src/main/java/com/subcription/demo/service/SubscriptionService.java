package com.subcription.demo.service;

import com.subcription.demo.dto.subscriptionRequestDTO;
import com.subcription.demo.entity.subscription;
import jakarta.validation.Valid;

import java.util.List;

public interface SubscriptionService {
    subscription createSubscription(@Valid subscriptionRequestDTO dto);
    List<subscription> getAllSubscriptions();
    subscriptionRequestDTO getSubscriptionById(Long id);
    subscriptionRequestDTO updateSubscription(Long id, @Valid subscriptionRequestDTO requestDTO);

    void deleteSubscripption(Long id);


}
