package com.aegis.safespace.review.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewDTO(
        UUID id,
        UUID locationId,
        UUID userId,
        String username,
        String profilePicture,
        short rating,
        String comment,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
