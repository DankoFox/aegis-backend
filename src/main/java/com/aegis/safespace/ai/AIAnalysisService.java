package com.aegis.safespace.ai;

import com.aegis.safespace.locationtag.dto.CreateLocationTagDTO;
import com.aegis.safespace.locationtag.service.LocationTagService;
import com.aegis.safespace.review.dto.ReviewDTO;
import com.aegis.safespace.review.event.ReviewCreatedEvent;
import com.aegis.safespace.review.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AIAnalysisService {

    private final ReviewService reviewService;

    private final LocationTagService locationTagService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${ai.host.url}")
    private String AI_HOST_URL;
//    private String AI_HOST_URL = "http://172.16.1.212:5000/analyze-review";

    private static final UUID LGBT_TAG_ID = UUID.fromString("955e8a63-802b-4ae4-923a-977ebc7cfda2");
    private static final UUID BLACK_TAG_ID = UUID.fromString("b3c20683-4afa-4fda-b6fc-3a995f6c2568");

    private static final double SAFE_THRESH_HOLD = 0.38;

    @EventListener
    @Async
    public void handleReviewCreatedEvent(ReviewCreatedEvent event) {
        try {
            analyzeLocation(event.getLocationId());
        } catch (Exception e) {
            // Log the error but don't fail the transaction
            System.err.println("AI analysis failed: " + e.getMessage());
        }
    }

    public Map<String, Object> analyzeLocation(UUID locationId) {
        // 0. Checking
        List<ReviewDTO> reviews = reviewService.getReviewsByLocation(locationId);

        if (reviews.isEmpty()) {
            throw new NoSuchElementException("No reviews found for location with id: " + locationId);
        }

        // 1. Build payload
        Map<String, Object> payload = new HashMap<>();

        payload.put("reviews", reviews.stream().map(r -> Map.of(
                "id", r.id(),
                "locationId", r.locationId(),
                "userId", r.userId(),
                "username", r.username(),
                "rating", r.rating(),
                "comment", r.comment(),
                "createdAt", r.createdAt(),
                "updatedAt", r.updatedAt()
        )).toList());

        // 2. Send to AI pipeline
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(AI_HOST_URL, request, Map.class);

        // 3. Get Response
        Map<String, Object> result = response.getBody();
        if (result == null) {
            throw new RuntimeException("Empty response from AI Service");
        }

        // 4. Process Response
        Double avgBlackFriendliness = ((Number) result.get("average_black_friendliness")).doubleValue();
        Double avgLgbtFriendliness = ((Number) result.get("average_lgbt_friendliness")).doubleValue();
        String summary = (String) result.get("summary");

        System.out.print(avgBlackFriendliness);
        System.out.print(avgLgbtFriendliness);

        updateLocationTagByFriendlinessScore(avgBlackFriendliness,BLACK_TAG_ID,locationId);
        updateLocationTagByFriendlinessScore(avgLgbtFriendliness, LGBT_TAG_ID,locationId);

        // 5. RETURN TYPE SHII
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("AI Service failed: " + response.getStatusCode());
        }
    }

    public void updateLocationTagByFriendlinessScore(Double friendlinessScore, UUID tagId, UUID locationID) {
        if (friendlinessScore > SAFE_THRESH_HOLD) {
            CreateLocationTagDTO createLocationTagDTO = new CreateLocationTagDTO();
            createLocationTagDTO.setLocationId(locationID);
            createLocationTagDTO.setTagId(tagId);

            try {
                locationTagService.create(createLocationTagDTO);
            } catch (IllegalArgumentException e) {}
        } else {
            try {
                locationTagService.deleteByLocationAndTag(locationID, tagId);
            } catch (EntityNotFoundException e) {}
        }
    }
}
