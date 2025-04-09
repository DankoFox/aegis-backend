package com.aegis.safespace.review.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewDTO(
        UUID id,
        UUID locationId,
        UUID userId,
        short rating,
        String comment,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
