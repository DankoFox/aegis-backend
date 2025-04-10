package com.aegis.safespace.review.service;

import com.aegis.safespace.location.model.Location;
import com.aegis.safespace.location.repository.LocationRepository;
import com.aegis.safespace.review.dto.CreateReviewDTO;
import com.aegis.safespace.review.dto.LocationRatingDTO;
import com.aegis.safespace.review.dto.ReviewDTO;
import com.aegis.safespace.review.model.Review;
import com.aegis.safespace.review.repository.ReviewRepository;
import com.aegis.safespace.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @Override
    public ReviewDTO createReview(CreateReviewDTO dto) {
        var user = userRepository.findById(dto.userId()).orElseThrow();
        var location = locationRepository.findById(dto.locationId()).orElseThrow();

        var review = Review.builder()
                .user(user)
                .location(location)
                .rating(dto.rating())
                .comment(dto.comment())
                .build();

        var saved = reviewRepository.save(review);
        return toDTO(saved);
    }

    @Override
    public List<ReviewDTO> getReviewsByLocation(UUID locationId) {
        return reviewRepository.findByLocationId(locationId).stream().map(this::toDTO).toList();
    }

    @Override
    public List<ReviewDTO> getReviewsByUser(UUID userId) {
        return reviewRepository.findByUserId(userId).stream().map(this::toDTO).toList();
    }

    private ReviewDTO toDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getLocation().getId(),
                review.getUser().getId(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }

    @Override
    public LocationRatingDTO getLocationRating(UUID locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + locationId));
        Double average = calculateLocationAvgRating(locationId);

        return new LocationRatingDTO(location.getId(), location.getName(), average);
    }

    public Double calculateLocationAvgRating(UUID locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + locationId));

        Double average = reviewRepository.findAverageRatingByLocationId(locationId);
        if (average == null) average = 0.0;
        average = Math.round(average * 10.0) / 10.0;

        return average;
    }
}
