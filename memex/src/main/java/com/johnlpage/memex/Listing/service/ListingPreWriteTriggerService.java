package com.johnlpage.memex.Listing.service;

import com.johnlpage.memex.Listing.model.Listing;
import com.johnlpage.memex.Listing.repository.ListingRepository;
import com.johnlpage.memex.generics.service.PreWriteTriggerService;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ListingPreWriteTriggerService
        extends PreWriteTriggerService<Listing> {

    private static final Logger LOG = LoggerFactory.getLogger(ListingPreWriteTriggerService.class);
    private final AtomicBoolean hasLoggedFirstCall = new AtomicBoolean(false);

    public ListingPreWriteTriggerService(ListingRepository repository) {
        // Initialize any required dependencies
    }

    /*
     * This code will be very specific to your data and
     * how you want to test it.
     * May not be required.
     */

    /** This Code is for mutable models */
    @Override
    public void modifyMutableDataPreWrite(Listing document) {
        document.setDescription(document.getDescription() + Instant.now().toString());
    }

    /* This Code is used for immutable models */
    /*
    @Override
    public Listing newImmutableDataPreWrite(Listing document) {
        // TODO: Implement your pre-write modifications for immutable models
        // Return a new instance with modifications
        return document;
    }
    */
}
