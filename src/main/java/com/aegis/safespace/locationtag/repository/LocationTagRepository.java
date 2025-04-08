package com.aegis.safespace.locationtag.repository;

import com.aegis.safespace.location.model.Location;
import com.aegis.safespace.locationtag.model.LocationTag;
import com.aegis.safespace.tag.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationTagRepository extends JpaRepository<LocationTag, UUID> {
    boolean existsByLocationAndTag(Location location, Tag tag);
    List<LocationTag> findByLocation_Id(UUID locationId);
    List<LocationTag> findByTag_Id(UUID tagId);

}
