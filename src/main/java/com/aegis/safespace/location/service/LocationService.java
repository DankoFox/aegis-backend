package com.aegis.safespace.location.service;

import com.aegis.safespace.location.dto.CreateLocationDTO;
import com.aegis.safespace.location.dto.UpdateLocationDTO;
import com.aegis.safespace.location.model.Location;

import java.util.List;
import java.util.UUID;

public interface LocationService {
    List<Location> getAllLocations();
    Location getLocationById(UUID id);
    Location createLocation(CreateLocationDTO dto);
    Location updateLocation(UUID id, UpdateLocationDTO dto);
    void deleteLocation(UUID id);
}
