package com.aegis.safespace.review.service;

import com.aegis.safespace.review.dto.CreateReviewDTO;
import com.aegis.safespace.review.dto.ReviewDTO;
import com.aegis.safespace.review.dto.LocationRatingDTO;


import java.util.List;
import java.util.UUID;

public interface ReviewService {
    ReviewDTO createReview(CreateReviewDTO dto);
    List<ReviewDTO> getReviewsByLocation(UUID locationId);
    List<ReviewDTO> getReviewsByUser(UUID userId);
    LocationRatingDTO getLocationRating(UUID locationId);
}
