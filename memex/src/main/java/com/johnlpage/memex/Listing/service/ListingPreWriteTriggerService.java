package com.johnlpage.memex.Listing.service;

import com.johnlpage.memex.Listing.model.Listing;
import com.johnlpage.memex.Listing.repository.ListingRepository;
import com.johnlpage.memex.generics.service.PreWriteTriggerService;
import org.springframework.stereotype.Service;

@Service
public class ListingPreWriteTriggerService
    extends PreWriteTriggerService<Listing> {

    public ListingPreWriteTriggerService(ListingRepository repository) {
        // Initialize any required dependencies
    }

    /*
     * This code will be very specific to your data and how you want to test it.
     * May not be required.
     */

    /** This Code is for mutable models */
    @Override
    public void modifyMutableDataPreWrite(Listing document) {
        // TODO: Implement your pre-write modifications for mutable models
        // Example:
        // - Validate and transform data
        // - Set default values
        // - Mark records for soft delete
        if(System.getenv("MODIFY_ON_LOAD") != null) {
            document.setDescription(document.getDescription() + " (updated)");
        }
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
