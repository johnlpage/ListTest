package com.johnlpage.memex.Listing.service;

import com.johnlpage.memex.Listing.model.Listing;
import com.johnlpage.memex.Listing.repository.ListingRepository;
import java.time.Instant;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;


// This is intended for downstream service that want to get reported on or perhaps
// augmented data

@Service
public class ListingHistoryService {
    private final ListingRepository repository;

    public ListingHistoryService(ListingRepository repository) {
        this.repository = repository;
    }

    public Stream<Listing> asOfDate(Long id, Instant asOfDate) {
        return repository.GetRecordByIdAsOfDate(id, asOfDate, Listing.class);
    }
}
