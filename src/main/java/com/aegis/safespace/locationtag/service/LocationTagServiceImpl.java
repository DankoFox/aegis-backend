package com.aegis.safespace.locationtag.service;

import com.aegis.safespace.location.model.Location;
import com.aegis.safespace.location.repository.LocationRepository;
import com.aegis.safespace.locationtag.dto.CreateLocationTagDTO;
import com.aegis.safespace.locationtag.dto.LocationDTO;
import com.aegis.safespace.locationtag.dto.LocationTagDTO;
import com.aegis.safespace.locationtag.dto.TagDTO;
import com.aegis.safespace.locationtag.model.LocationTag;
import com.aegis.safespace.locationtag.repository.LocationTagRepository;
import com.aegis.safespace.locationtag.service.LocationTagService;
import com.aegis.safespace.tag.model.Tag;
import com.aegis.safespace.tag.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocationTagServiceImpl implements LocationTagService {

    private final LocationTagRepository locationTagRepository;
    private final LocationRepository locationRepository;
    private final TagRepository tagRepository;

    @Autowired
    public LocationTagServiceImpl(
            LocationTagRepository locationTagRepository,
            LocationRepository locationRepository,
            TagRepository tagRepository
    ) {
        this.locationTagRepository = locationTagRepository;
        this.locationRepository = locationRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<LocationTagDTO> getAllLocationTags() {
        return locationTagRepository.findAll().stream()
                .map(lt -> new LocationTagDTO(
                        lt.getId(),
                        lt.getLocation().getId(),
                        lt.getTag().getId(),
                        lt.getCreatedAt(),
                        lt.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public LocationTag getById(UUID id) {
        return locationTagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LocationTag not found with id: " + id));
    }

    @Override
    public LocationTag create(CreateLocationTagDTO dto) {
        Location location = locationRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));
        Tag tag = tagRepository.findById(dto.getTagId())
                .orElseThrow(() -> new EntityNotFoundException("Tag not found"));

        if (locationTagRepository.existsByLocationAndTag(location, tag)) {
            throw new IllegalArgumentException("Location already tagged with this tag");
        }

        LocationTag locationTag = LocationTag.builder()
                .location(location)
                .tag(tag)
                .build();

        return locationTagRepository.save(locationTag);
    }

    @Override
    public void delete(UUID id) {
        locationTagRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<TagDTO> getTagsByLocation(UUID locationId) {
        List<LocationTag> locationTags = locationTagRepository.findByLocation_Id(locationId);
        return locationTags.stream()
                .map(lt -> {
                    Tag tag = lt.getTag();
                    return new TagDTO(tag.getId(), tag.getName());
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LocationDTO> getLocationsByTag(UUID tagId) {
        List<LocationTag> locationTags = locationTagRepository.findByTag_Id(tagId);
        return locationTags.stream()
                .map(lt -> {
                    Location loc = lt.getLocation();
                    return new LocationDTO(
                            loc.getId(),
                            loc.getName(),
                            loc.getAddress(),
                            loc.getLatitude(),
                            loc.getLongitude()
                    );
                })
                .toList();
    }
}
