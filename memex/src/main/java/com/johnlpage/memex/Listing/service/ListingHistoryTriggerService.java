package com.johnlpage.memex.Listing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnlpage.memex.Listing.model.Listing;
import com.johnlpage.memex.generics.service.HistoryTriggerService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

// This is just Layering Glue, Business logic goes here not direct database access
// That goes via the Repository, you may choose to add logging here for example

@Service
public class ListingHistoryTriggerService
    extends HistoryTriggerService<Listing> {

    public ListingHistoryTriggerService(
            MongoTemplate mongoTemplate, ObjectMapper objectMapper) {
        super(mongoTemplate, objectMapper);
    }
}
