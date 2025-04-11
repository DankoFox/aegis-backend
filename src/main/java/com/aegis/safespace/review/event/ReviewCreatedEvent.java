package com.aegis.safespace.review.event;

import java.util.UUID;

public class ReviewCreatedEvent {
    private final UUID locationId;

    public ReviewCreatedEvent(UUID locationId) {
        this.locationId = locationId;
    }

    public UUID getLocationId() {
        return locationId;
    }
}