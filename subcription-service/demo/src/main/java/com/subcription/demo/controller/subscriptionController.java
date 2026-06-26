package com.subcription.demo.controller;

import com.subcription.demo.dto.SubscriptionWithUserDto;
import com.subcription.demo.dto.subscriptionRequestDTO;
import com.subcription.demo.dto.subscriptionResponseDto;
import com.subcription.demo.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

@RequestMapping("/subscription")
@RequiredArgsConstructor
public class subscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/create")
    public ResponseEntity<subscriptionResponseDto> createSubscription(@Valid @RequestBody subscriptionRequestDTO requestDTO) {
        return new ResponseEntity<>(subscriptionService.createSubscription(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<subscriptionResponseDto>> getAllSubscriptions() {

        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<subscriptionResponseDto> getSubscriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<SubscriptionWithUserDto>> getSubscriptionsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByUserId(userId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<subscriptionResponseDto> updateSubscription(@PathVariable Long id, @Valid @RequestBody subscriptionRequestDTO requestDTO) {
        return ResponseEntity.ok(subscriptionService.updateSubscription(id, requestDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        subscriptionService.deleteSubscripption(id);
        return ResponseEntity.noContent().build();
    }
}
