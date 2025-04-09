package com.aegis.safespace.review.repository;

import com.aegis.safespace.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByLocationId(UUID locationId);
    List<Review> findByUserId(UUID userId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.location.id = :locationId")
    Double findAverageRatingByLocationId(@Param("locationId") UUID locationId);
}
