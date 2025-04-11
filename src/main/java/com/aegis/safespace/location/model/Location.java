package com.aegis.safespace.location.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "locations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Location {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "google_place_id")
    private String googlePlaceId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false, columnDefinition = "text")
    private String address;

    @Column(name = "latitude", nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "thumbnail_image", nullable = false, columnDefinition = "TEXT DEFAULT 'https://media.istockphoto.com/id/1251099241/vi/anh/t%C3%B4i-kh%C3%B4ng-bi%E1%BA%BFt-ch%C3%A2n-dung-nam-thanh-ni%C3%AAn-b%E1%BB%91i-r%E1%BB%91i-m%E1%BA%B7c-%C3%A1o-thun-xanh-%C4%91%E1%BB%A9ng-nh%C3%AAn-vai-dang-tay-c%C3%A1ch-ly.jpg?s=1024x1024&w=is&k=20&c=-imtbRU3tEpeMMyS70abQrXoy1lIxSXBLnoZjiWaPXk='")
    private String thumbnailImage;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
