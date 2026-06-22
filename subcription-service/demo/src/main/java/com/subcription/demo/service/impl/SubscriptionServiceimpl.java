package com.subcription.demo.service.impl;

import com.subcription.demo.dto.subscriptionRequestDTO;
import com.subcription.demo.entity.subscription;
import com.subcription.demo.repository.SubscriptionRepository;
import com.subcription.demo.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor



public class SubscriptionServiceimpl implements SubscriptionService {



    private final SubscriptionRepository subscriptionRepository;


    @Override
    public subscription createSubscription(subscriptionRequestDTO dto) {
        

        return null;
    }

    @Override
    public List<subscription> getAllSubscriptions() {
        return List.of();
    }

    @Override
    public subscriptionRequestDTO getSubscriptionById(Long id) {
        return null;
    }

    @Override
    public subscriptionRequestDTO updateSubscription(Long id, subscriptionRequestDTO requestDTO) {
        return null;
    }

    @Override
    public void deleteSubscripption(Long id) {


    }
}
