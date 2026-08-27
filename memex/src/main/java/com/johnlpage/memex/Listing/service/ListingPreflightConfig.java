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
    public List<Document> getSearchIndexes() {


        String SEARCH_INDEXES = """
                { "searchIndexes": [
                    {
                        "name": "default",
                        "definition": {
                            "mappings": {
                                "dynamic": true,
                                "fields": {}
                            }
                        }
                    }
                ]}
                """;

        return Document.parse(SEARCH_INDEXES).getList("searchIndexes", Document.class);
    }
}
