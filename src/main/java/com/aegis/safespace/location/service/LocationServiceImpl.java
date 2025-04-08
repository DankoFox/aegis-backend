package com.aegis.safespace.location.service;

import com.aegis.safespace.location.dto.CreateLocationDTO;
import com.aegis.safespace.location.dto.UpdateLocationDTO;
import com.aegis.safespace.location.model.Location;
import com.aegis.safespace.location.repository.LocationRepository;
import com.aegis.safespace.location.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationById(UUID id) {
        return locationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Location not found with id: " + id));
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
        Location location = getLocationById(id);
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
