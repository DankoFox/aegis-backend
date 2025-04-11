//package com.aegis.safespace.locationinsight.model;
//
//import com.aegis.safespace.location.model.Location;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Entity
//@Table(name = "location_insights")
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class LocationInsight {
//
//    @Id
//    private UUID locationId;
//
//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "location_id")
//    private Location location;
//
//    @Column(name = "average_friendliness")
//    private Double averageFriendliness;
//
//    @Column(name = "summary", columnDefinition = "TEXT")
//    private String summary;
//
//    @Column(name = "verdict")
//    private String verdict;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//}
