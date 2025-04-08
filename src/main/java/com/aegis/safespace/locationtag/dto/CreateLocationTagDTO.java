package com.aegis.safespace.locationtag.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateLocationTagDTO {
    @NotNull
    private UUID locationId;

    @NotNull
    private UUID tagId;
}