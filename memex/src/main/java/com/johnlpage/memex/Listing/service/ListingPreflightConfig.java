package com.johnlpage.memex.Listing.service;

import com.johnlpage.memex.Listing.model.Listing;
import  com.johnlpage.memex.generics.service.CollectionPreflightConfig;
import com.mongodb.client.model.IndexModel;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListingPreflightConfig implements CollectionPreflightConfig {

    @Override
    public String getCollectionName() {
        return "listing";
    }

    @Override
    public Class<?> getSchemaClass() {
        return Listing.class;
    }


    @Override
    public List<IndexModel> getIndexes() {
        return List.of(
                // Simple ascending index
                // new IndexModel(Indexes.ascending("some.field"))
        );
    }

    @Override
    public List<String> getShardKeyFields() {
        // Shard key is { city, _id }. _id is always added to write queries automatically,
        // so only the other shard key field(s) need to be listed here.
       return  List.of();
        // return List.of("city");
    }


    @Override
    public List<Document> getSearchIndexes() {


        // Explicit (non-dynamic) field list covering only the fields a
        // consumer-facing real-estate search (e.g. realtor.com-style filters
        // and keyword search) would query on. Deliberately excludes arrays
        // (priceHistory, schools, nearbyHomes, photos, etc.), URLs, and the
        // redundant nested `address` object (duplicates top-level
        // city/state/streetAddress/zipcode). Most fields use an empty `{}`
        // mapping so Atlas Search auto-detects the appropriate type; city,
        // streetAddress and abbreviatedAddress additionally get an
        // "autocomplete" type for type-ahead search-box support.
        String SEARCH_INDEXES = """
                { "searchIndexes": [
                    {
                        "name": "default",
                        "definition": {
                            "mappings": {
                                "dynamic": false,
                                "fields": {
                                    "city":                  [ {}, { "type": "autocomplete" } ],
                                    "state":                 {},
                                    "zipcode":               {},
                                    "county":                {},
                                    "streetAddress":         [ {}, { "type": "autocomplete" } ],
                                    "abbreviatedAddress":    [ {}, { "type": "autocomplete" } ],

                                    "price":                 {},
                                    "zestimate":             {},
                                    "rentZestimate":         {},
                                    "lastSoldPrice":         {},

                                    "bedrooms":              {},
                                    "bathrooms":             {},
                                    "livingArea":            {},
                                    "lotSize":               {},
                                    "yearBuilt":             {},

                                    "homeType":              {},
                                    "propertyTypeDimension": {},
                                    "homeStatus":            {},
                                    "listingTypeDimension":  {},
                                    "tag":                   {},

                                    "daysOnZillow":          {},
                                    "dateSold":              {},

                                    "hoa_details": {
                                        "type": "document",
                                        "fields": {
                                            "has_hoa":        {},
                                            "hoa_fee_value":  {},
                                            "hoa_fee_period": {}
                                        }
                                    },

                                    "description": { "type": "string" },

                                    "zpid":                  {},
                                    "listingId":             {}
                                }
                            }
                        }
                    }
                ]}
                """;

        return Document.parse(SEARCH_INDEXES).getList("searchIndexes", Document.class);
    }
}
