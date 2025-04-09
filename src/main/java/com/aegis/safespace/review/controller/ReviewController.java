package com.aegis.safespace.review.controller;

import com.aegis.safespace.review.dto.CreateReviewDTO;
import com.aegis.safespace.review.dto.LocationRatingDTO;
import com.aegis.safespace.review.dto.ReviewDTO;
import com.aegis.safespace.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody CreateReviewDTO dto) {
        return ResponseEntity.ok(reviewService.createReview(dto));
    }

    @GetMapping("/by-location/{locationId}")
    public ResponseEntity<List<ReviewDTO>> getByLocation(@PathVariable UUID locationId) {
        return ResponseEntity.ok(reviewService.getReviewsByLocation(locationId));
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<ReviewDTO>> getByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(userId));
    }

    @GetMapping("/average-rating/{locationId}")
    public ResponseEntity<LocationRatingDTO> getAverageRating(@PathVariable UUID locationId) {
        return ResponseEntity.ok(reviewService.getLocationRating(locationId));
    }

}
