package com.subcription.demo.controller;

import com.subcription.demo.dto.subscriptionRequestDTO;
import com.subcription.demo.entity.subscription;
import com.subcription.demo.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subscription")
@RequiredArgsConstructor


public class subscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/create")
    public ResponseEntity<subscription> createSubscription(@Valid @RequestBody subscriptionRequestDTO requestDTO) {
        // Call the service layer to create a new subscription

        return new ResponseEntity<>(subscriptionService.createSubscription(requestDTO), HttpStatus.CREATED);


    }

    @GetMapping("/getAll")

       public ResponseEntity<List<subscription>> getAllSubscriptions() {
            // Call the service layer to retrieve all subscriptions
            return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<subscriptionRequestDTO> getSubscriptionById(@PathVariable Long id) {
        // Call the service layer to retrieve a subscription by ID
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
    }

    @DeleteMapping("delete/{id}")

    public  ResponseEntity<Void> deleteGroup(@PathVariable Long id){
        subscriptionService.deleteSubscripption(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<subscriptionRequestDTO> updateSubscription(@PathVariable Long id, @Valid @RequestBody subscriptionRequestDTO requestDTO) {
        // Call the service layer to update a subscription
        return ResponseEntity.ok(subscriptionService.updateSubscription(id, requestDTO));
    }



}

