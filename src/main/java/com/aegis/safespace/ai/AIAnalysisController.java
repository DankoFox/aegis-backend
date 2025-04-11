package com.aegis.safespace.ai;

import com.aegis.safespace.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ai-analyze")
@RequiredArgsConstructor
public class AIAnalysisController {

    private final AIAnalysisService aiAnalysisService;
    private final ReviewService reviewService;

    @GetMapping("/{locationId}")
    public ResponseEntity<Map<String, Object>> analyzeLocation(@PathVariable UUID locationId) {
        Map<String, Object> result = aiAnalysisService.analyzeLocation(locationId);
        return ResponseEntity.ok(result);
    }
}
