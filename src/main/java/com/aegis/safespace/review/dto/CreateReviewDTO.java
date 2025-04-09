package com.aegis.safespace.review.dto;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record CreateReviewDTO(
        @NotNull UUID locationId,
        @NotNull UUID userId,
        @Min(1) @Max(5) short rating,
        @NotBlank String comment
) {}
