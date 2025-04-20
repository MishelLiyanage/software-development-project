package com.SDP.project.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String request) {
        // Parse and handle PayPal webhook event
        // For example:
        // 1. Deserialize the event from JSON
        // 2. Check the event type (payment capture, refund, etc.)
        // 3. Update your system accordingly

        return ResponseEntity.ok("Webhook received and processed.");
    }
}