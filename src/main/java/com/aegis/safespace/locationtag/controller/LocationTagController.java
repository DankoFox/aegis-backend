package com.aegis.safespace.locationtag.controller;

import com.aegis.safespace.location.model.Location;
import com.aegis.safespace.locationtag.dto.CreateLocationTagDTO;
import com.aegis.safespace.locationtag.dto.LocationDTO;
import com.aegis.safespace.locationtag.dto.LocationTagDTO;
import com.aegis.safespace.locationtag.dto.TagDTO;
import com.aegis.safespace.locationtag.model.LocationTag;
import com.aegis.safespace.locationtag.service.LocationTagService;
import com.aegis.safespace.tag.model.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/location-tags")
public class LocationTagController {

    private final LocationTagService locationTagService;

    @Autowired
    public LocationTagController(LocationTagService locationTagService) {
        this.locationTagService = locationTagService;
    }

    @GetMapping
    public ResponseEntity<List<LocationTagDTO>> getAll() {
        return ResponseEntity.ok(locationTagService.getAllLocationTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationTag> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(locationTagService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LocationTag> create(@Valid @RequestBody CreateLocationTagDTO dto) {
        return new ResponseEntity<>(locationTagService.create(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        locationTagService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tags-by-location/{locationId}")
    public ResponseEntity<List<TagDTO>> getTagsByLocation(@PathVariable UUID locationId) {
        List<TagDTO> tags = locationTagService.getTagsByLocation(locationId);
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/locations-by-tag/{tagId}")
    public ResponseEntity<List<LocationDTO>> getLocationsByTag(@PathVariable UUID tagId) {
        return ResponseEntity.ok(locationTagService.getLocationsByTag(tagId));
    }

}
