package com.aegis.safespace.location.dto;

import com.aegis.safespace.locationtag.dto.TagDTO;
import com.aegis.safespace.review.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailLocationDTO {
    private UUID id;
    private String name;
    private String address;
    private String thumbnailImage;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Double averageRating;
    private List<TagDTO> tags;
    private List<ReviewDTO> reviews;
}
