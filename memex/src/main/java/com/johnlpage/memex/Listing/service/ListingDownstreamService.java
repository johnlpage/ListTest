package com.johnlpage.memex.Listing.service;

import com.johnlpage.memex.Listing.model.Listing;
import com.johnlpage.memex.Listing.repository.ListingRepository;
import java.util.stream.Stream;
import org.bson.json.JsonObject;
import org.springframework.stereotype.Service;

// This is intended for downstream service that want to get reported on or perhaps
// augmented data

@Service
public class ListingDownstreamService {
    private final ListingRepository repository;

    public ListingDownstreamService(ListingRepository repository) {
        this.repository = repository;
    }

    public Stream<JsonObject> nativeJsonExtractStream(String formatRequired) {
        return repository.nativeJsonExtract(formatRequired, Listing.class);
    }

    public Stream<Listing> jsonExtractStream() {
        return repository.findAllBy();
    }
}
