package com.aegis.safespace.location.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateLocationDTO {

    private String googlePlaceId;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @Nullable
    private String thumbnailImage;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;
}
