package com.aegis.safespace.location.service;

import com.aegis.safespace.location.dto.CreateLocationDTO;
import com.aegis.safespace.location.dto.DetailLocationDTO;
import com.aegis.safespace.location.dto.SummaryLocationDTO;
import com.aegis.safespace.location.dto.UpdateLocationDTO;
import com.aegis.safespace.location.model.Location;
import com.aegis.safespace.location.repository.LocationRepository;
import com.aegis.safespace.locationtag.dto.TagDTO;
import com.aegis.safespace.locationtag.service.LocationTagService;
import com.aegis.safespace.review.dto.ReviewDTO;
import com.aegis.safespace.review.service.ReviewServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private LocationTagService locationTagService;
    private ReviewServiceImpl reviewService;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, LocationTagService locationTagService, ReviewServiceImpl reviewService)
    {
        this.locationRepository = locationRepository;
        this.locationTagService = locationTagService;
        this.reviewService = reviewService;
    }

    @Override
    public List<SummaryLocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(location -> {
            List<TagDTO> tags = locationTagService.getTagsByLocation(location.getId());

            Double averageRating = reviewService.calculateLocationAvgRating(location.getId());

            return new SummaryLocationDTO(
                    location.getId(),
                    location.getName(),
                    location.getAddress(),
                    location.getLatitude(),
                    location.getLongitude(),
                    averageRating,
                    tags
            );
        }).toList();
    }

    @Override
    public DetailLocationDTO getLocationById(UUID id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + id));

        List<TagDTO> tags = locationTagService.getTagsByLocation(id);
        Double averageRating = reviewService.calculateLocationAvgRating(id);
        List<ReviewDTO> reviews = reviewService.getReviewsByLocation(id);

        return new DetailLocationDTO(
                location.getId(),
                location.getName(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                averageRating,
                tags,
                reviews
        );
    }

    @Override
    public Location createLocation(CreateLocationDTO dto) {
        Location location = Location.builder()
                .googlePlaceId(dto.getGooglePlaceId())
                .name(dto.getName())
                .address(dto.getAddress())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();

        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(UUID id, UpdateLocationDTO dto) {
        Location location = locationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Location not found with id: " + id));

        location.setGooglePlaceId(dto.getGooglePlaceId());
        location.setName(dto.getName());
        location.setAddress(dto.getAddress());
        location.setLatitude(dto.getLatitude());
        location.setLongitude(dto.getLongitude());
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(UUID id) {
        locationRepository.deleteById(id);
    }
}
