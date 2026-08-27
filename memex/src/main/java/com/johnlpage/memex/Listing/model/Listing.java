package com.johnlpage.memex.Listing.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.johnlpage.memex.util.DeleteFlag;
import com.johnlpage.memex.util.ObjectConverter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Root document class for the 'listing' collection.
 * <p>
 * Features:
 * - Optimistic locking via @Version
 * - Soft delete support via @DeleteFlag
 * - Flexible schema via payload map for unmapped fields
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
@Document(collection = "listing")
public class Listing {

    @Id
    @EqualsAndHashCode.Include
    private String listingId;

    /**
     * Optimistic locking version field.
     * Automatically incremented by Spring Data MongoDB on each save.
     */
    @Field("lock_version")    @Version
    private Long lockVersion;

    private String state;

    private ListingAddress address;

    private String listingTypeDimension;

    private String livingAreaUnitsShort;

    private String livingAreaUnits;

    private String listingDataSource;

    private String tag;

    private Integer rentZestimate;

    private String streetViewServiceUrl;

    private List<ListingPriceHistory> priceHistory;

    private List<ListingFinancial> financial;

    private Integer restimateLowPercent;

    private ListingInterior interior;

    private ListingListingProvidedBy listing_provided_by;

    private String homeType;

    private String propertyTypeDimension;

    private ListingHoaDetails hoa_details;

    private Integer taxAssessedYear;

    private ListingMortgageRates mortgageRates;

    private Integer daysOnZillow;

    private Integer lotSize;

    private List<ListingNearbyZipcodes> nearbyZipcodes;

    private Integer tourViewCount;

    private List<ListingNearbyNeighborhoods> nearbyNeighborhoods;

    private List<ListingHomeValuation> homeValuation;

    private Integer countyFIPS;

    private ListingCitySearchUrl citySearchUrl;

    private Boolean isRentalListingOffMarket;

    private String hdpUrl;

    private String description;

    private List<ListingNearbyHomes> nearbyHomes;

    private String streetViewMetadataUrlMediaWallLatLong;

    private Boolean isListingClaimedByCurrentSignedInUser;

    private Boolean isCurrentSignedInAgentResponsible;

    private Boolean isCurrentSignedInUserVerifiedOwner;

    private String isVerifiedClaimedByCurrentSignedInUser;

    private Boolean isUndisclosedAddress;

    private String currency;

    private Boolean hideZestimate;

    private String country;

    private Boolean isPremierBuilder;

    private Boolean isZillowOwned;

    private Boolean hasPublicVideo;

    private String isInstantOfferEnabled;

    private String rentalApplicationsAcceptedType;

    private ListingSelfTour selfTour;

    private Boolean isFeatured;

    private Boolean isHousingConnector;

    private Boolean isRentalsLeadCapMet;

    private Boolean is_showcased;

    private Boolean is_listed_by_management_company;

    private String abbreviatedAddress;

    private String lotAreaUnits;

    private String brokerageName;

    private Integer countyID;

    private String streetViewMetadataUrlMediaWallAddress;

    private Integer restimateMinus30;

    private Integer yearBuilt;

    private Integer zestimateHighPercent;

    private Integer restimateHighPercent;

    private Integer zestimateLowPercent;

    private List<ListingConstruction> construction;

    private LocalDate timestamp;

    private String timeZone;

    private Double longitude;

    private Double lotAreaValue;

    private String streetAddress;

    private List<ListingInteriorFull> interior_full;

    private String hdpTypeDimension;

    private Boolean isOffMarket;

    private List<ListingNearbyCities> nearbyCities;

    private String url;

    private String streetViewTileImageUrlMediumLatLong;

    private List<ListingTaxHistory> taxHistory;

    private String homeStatus;

    private Integer bedrooms;

    private Integer zpid;

    private String resofacts_water_source;

    private String resofacts_sewer;

    private Integer photoCount;

    private Integer livingArea;

    private Integer livingAreaValue;

    private ListingOverview overview;

    private Integer zipcode;

    private List<ListingSchools> schools;

    private Double latitude;

    private String county;

    private Integer lastSoldPrice;

    private String city;

    private Integer bathrooms;

    private LocalDate dateSoldString;

    private Integer price;

    private Boolean hasBadGeocode;

    private Boolean hasApprovedThirdPartyVirtualTourUrl;

    private Boolean isNonOwnerOccupied;

    private ListingTourEligibility tourEligibility;

    private LocalDate dateSold;

    private List<ListingProperty> property;

    private List<ListingPhotos> photos;

    private Double propertyTaxRate;

    private Integer zestimate;

    private Integer ssid;

    private String virtualTourUrl;

    /**
     * Use this to flag from the JSON that we want to remove the record.
     * Not persisted to MongoDB.
     */
    @Transient
    @DeleteFlag
    private Boolean deleted;

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
