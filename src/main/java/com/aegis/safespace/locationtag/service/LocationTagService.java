package com.aegis.safespace.locationtag.service;

import com.aegis.safespace.locationtag.dto.CreateLocationTagDTO;
import com.aegis.safespace.locationtag.dto.LocationDTO;
import com.aegis.safespace.locationtag.dto.LocationTagDTO;
import com.aegis.safespace.locationtag.dto.TagDTO;
import com.aegis.safespace.locationtag.model.LocationTag;

import java.util.List;
import java.util.UUID;

public interface LocationTagService {
    List<LocationTagDTO> getAllLocationTags();
    LocationTag getById(UUID id);
    LocationTag create(CreateLocationTagDTO dto);
    void delete(UUID id);
    List<TagDTO> getTagsByLocation(UUID locationId);
    List<LocationDTO> getLocationsByTag(UUID tagId);
}
