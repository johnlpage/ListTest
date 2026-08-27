package com.johnlpage.memex.Listing.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.johnlpage.memex.util.ObjectConverter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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
public class ListingHomeValuationComparablesCompsProperty {

    private ListingHomeValuationComparablesCompsPropertyAddress address;

    private ListingHomeValuationComparablesCompsPropertyAttributionInfo attributionInfo;

    private Double longitude;

    private Double lotAreaValue;

    private Integer daysOnZillow;

    private String homeStatus;

    private Double zpid;

    private List<ListingHomeValuationComparablesCompsPropertyCompsCarouselPropertyPhotos> compsCarouselPropertyPhotos;

    private Double latitude;

    private String hdpUrl;

    private Integer lastSoldPrice;

    private Integer bathrooms;

    private Integer price;

    private String currency;

    private String livingAreaUnits;

    private String livingAreaUnitsShort;

    private ListingHomeValuationComparablesCompsPropertyListingSubType listing_sub_type;

    private Double livingAreaValue;

    private LocalDate dateSold;

    private Boolean isUndisclosedAddress;

    private String lotAreaUnits;

    private Integer bedrooms;

    private Integer zestimate;

    private String homeType;

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
