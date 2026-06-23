package com.subcription.demo.mapper;

import com.subcription.demo.dto.subscriptionRequestDTO;
import com.subcription.demo.dto.subscriptionResponseDto;
import com.subcription.demo.entity.subscription;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {
    subscription toEntity(subscriptionRequestDTO dto);
    subscriptionResponseDto toDto(subscription subscription);
}
