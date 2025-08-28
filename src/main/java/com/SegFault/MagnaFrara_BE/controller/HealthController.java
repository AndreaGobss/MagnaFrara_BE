package com.SegFault.MagnaFrara_BE.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping
    public Map<String, Object> health() {
        return Map.of(
            "success", true,
            "data", Map.of("status", "OK"),
            "message", "Backend up"
        );
    }
}
