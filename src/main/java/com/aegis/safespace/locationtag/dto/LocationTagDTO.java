package com.aegis.safespace.locationtag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationTagDTO {
    private UUID id;
    private UUID locationId;
    private UUID tagId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
