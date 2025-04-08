package com.aegis.safespace.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for system status and health check endpoints
 */
@RestController
@RequestMapping("/status")
public class StatusController {

    /**
     * Basic health check endpoint that returns the current system status
     * and application information
     *
     * @return Status information including timestamp and application state
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("status", "UP");
        status.put("timestamp", LocalDateTime.now().toString());
        status.put("service", "SafeSpace API");
        status.put("version", "1.0.0");

        return ResponseEntity.ok(status);
    }
}