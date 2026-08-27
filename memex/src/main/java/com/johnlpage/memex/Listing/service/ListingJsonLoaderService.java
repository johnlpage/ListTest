package com.johnlpage.memex.Listing.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnlpage.memex.Listing.model.Listing;
import com.johnlpage.memex.generics.repository.OptimizedMongoLoadRepository;
import com.johnlpage.memex.generics.service.MongoDbJsonStreamingLoaderService;
import org.springframework.stereotype.Service;

// This is just Layering Glue, Business logic goes here not direct database access
// That goes via the Repository, you may choose to add logging here for example

@Service
public class ListingJsonLoaderService
    extends MongoDbJsonStreamingLoaderService<Listing> {

    public ListingJsonLoaderService(
            OptimizedMongoLoadRepository<Listing> repository,
            ObjectMapper objectMapper,
            JsonFactory jsonFactory) {
        super(repository, objectMapper, jsonFactory);
    }
}
