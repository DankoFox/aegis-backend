package com.aegis.safespace.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class LocationRatingDTO {
    private UUID locationId;
    private String locationName;
    private Double averageRating;
}
