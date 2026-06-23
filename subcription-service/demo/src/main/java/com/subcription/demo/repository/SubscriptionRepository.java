package com.subcription.demo.repository;

import com.subcription.demo.entity.subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<subscription, Long> {
    List<subscription> findByUserId(Long userId);
}
