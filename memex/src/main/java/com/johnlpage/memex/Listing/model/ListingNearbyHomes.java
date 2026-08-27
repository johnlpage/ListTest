package com.johnlpage.memex.Listing.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.johnlpage.memex.util.ObjectConverter;
import java.util.HashMap;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Embedded document class.
 * </p>
 * <p>
 * All classes include a 'payload' map to capture unmapped fields,
 * supporting schema flexibility and evolution.
 * </p>
 * Generated from JSON sample - review and adjust as needed.
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ListingNearbyHomes {

    private ListingNearbyHomesAddress address;

    private String state;

    private String listingTypeDimension;

    private Integer lotSize;

    private String homeStatus;

    private Integer zpid;

    private Double latitude;

    private String hdpUrl;

    private String hdpTypeDimension;

    private Double longitude;

    private Double lotAreaValue;

    private Double bathrooms;

    private ListingNearbyHomesAttributionInfo attributionInfo;

    private Integer price;

    private String currency;

    private String livingAreaUnits;

    private String livingAreaUnitsShort;

    private ListingNearbyHomesListingMetadata listingMetadata;

    private ListingNearbyHomesListingSubType listing_sub_type;

    private String homeType;

    private String propertyTypeDimension;

    private Integer livingAreaValue;

    private Boolean isShowcaseListing;

    private Boolean isPremierBuilder;

    private String lotAreaUnits;

    private Integer bedrooms;

    private Integer livingArea;

    /**
     * Captures any fields not explicitly mapped to class fields.
     * Supports schema flexibility and evolution.
     * Only persisted/serialized when non-empty.
     */
    @Field(write = Field.Write.NON_NULL)
    private Map<String, Object> payload;

    @JsonAnySetter
    public void set(String key, Object value) {
        if (payload == null) {
            payload = new HashMap<String, Object>();
        }
        payload.put(key, ObjectConverter.convertObject(value));
    }

    @JsonAnyGetter
    public Map<String, Object> getPayload() {
        return payload;
    }

    /**
     * Helper method to safely add to payload from your own code
     */
    public void addToPayload(String key, Object value) {
        set(key, value);
    }
}
