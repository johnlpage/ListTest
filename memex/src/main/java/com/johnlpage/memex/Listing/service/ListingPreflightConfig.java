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
        // (priceHistory, schools, nearbyHomes, photos, etc.), URLs, the
        // redundant nested `address` object (duplicates top-level
        // city/state/streetAddress/zipcode), and `listingId` (that field is
        // @Id-annotated so Spring Data actually stores it under the "_id"
        // key, not "listingId" - indexing it by its Java field name would
        // silently match nothing; exact _id lookups don't need Atlas Search
        // anyway). Atlas Search requires an explicit "type" on every field
        // you list statically here - true per-field dynamic type inference
        // only exists for a whole embedded document subtree (see
        // hoa_details below) or for fields left out of "fields" entirely
        // under a top-level "dynamic": true, which isn't what we want since
        // we're deliberately restricting to this subset.
        String SEARCH_INDEXES = """
                { "searchIndexes": [
                    {
                        "name": "default",
                        "definition": {
                            "mappings": {
                                "dynamic": false,
                                "fields": {
                                    "city":                  { "type": "string" },
                                    "state":                 { "type": "string" },
                                    "zipcode":               { "type": "number" },
                                    "county":                { "type": "string" },
                                    "streetAddress":         { "type": "string" },
                                    "abbreviatedAddress":    { "type": "string" },

                                    "price":                 { "type": "number" },
                                    "zestimate":             { "type": "number" },
                                    "rentZestimate":         { "type": "number" },
                                    "lastSoldPrice":         { "type": "number" },

                                    "bedrooms":              { "type": "number" },
                                    "bathrooms":             { "type": "number" },
                                    "livingArea":            { "type": "number" },
                                    "lotSize":               { "type": "number" },
                                    "yearBuilt":             { "type": "number" },

                                    "homeType":              { "type": "string" },
                                    "propertyTypeDimension": { "type": "string" },
                                    "homeStatus":            { "type": "string" },
                                    "listingTypeDimension":  { "type": "string" },
                                    "tag":                   { "type": "string" },

                                    "daysOnZillow":          { "type": "number" },
                                    "dateSold":              { "type": "date" },

                                    "hoa_details": {
                                        "type": "document",
                                        "dynamic": true
                                    },

                                    "description":           { "type": "string" },

                                    "zpid":                  { "type": "number" }
                                }
                            }
                        }
                    }
                ]}
                """;

        return Document.parse(SEARCH_INDEXES).getList("searchIndexes", Document.class);
    }
}
