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
public class ListingNearbyHomesListingMetadata {

    private Boolean comminglingCategoryIsRulesApplicable;

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
