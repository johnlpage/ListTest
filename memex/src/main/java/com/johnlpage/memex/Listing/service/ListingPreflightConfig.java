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


        // "dynamic": true means every field is auto-indexed with its
        // automatically-detected type (avoids ever having to hand-specify a
        // "type" for scalar fields like bedrooms/bathrooms/price - Atlas
        // requires an explicit type on any field you list yourself, but
        // fields left out of "fields" entirely are covered by dynamic
        // auto-detection instead). The "fields" entries below are additive
        // overrides layered on top of that dynamic behavior, used only for
        // the address fields that need "autocomplete" support (type-ahead
        // search box) in addition to their normal auto-detected type.
        String SEARCH_INDEXES = """
                { "searchIndexes": [
                    {
                        "name": "default",
                        "definition": {
                            "mappings": {
                                "dynamic": true,
                                "fields": {
                                    "city":               { "type": "autocomplete" },
                                    "streetAddress":      { "type": "autocomplete" },
                                    "abbreviatedAddress":  { "type": "autocomplete" }
                                }
                            }
                        }
                    }
                ]}
                """;

        return Document.parse(SEARCH_INDEXES).getList("searchIndexes", Document.class);
    }
}
