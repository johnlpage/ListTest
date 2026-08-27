package com.johnlpage.memex.Listing.service;

import com.johnlpage.memex.Listing.model.Listing;
import com.johnlpage.memex.generics.service.InvalidDataHandlerService;
import jakarta.validation.ConstraintViolation;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ListingInvalidDataHandlerService
    extends InvalidDataHandlerService<Listing> {

    private static final Logger LOG =
        LoggerFactory.getLogger(ListingInvalidDataHandlerService.class);

    public boolean handleInvalidData(
            Listing document,
            Set<ConstraintViolation<Listing>> violations,
            Class<Listing> clazz) {

        LOG.warn(
            "Invalid data detected in document, but no explicit handler provided, "
                + "discarding. in ListingInvalidDataHandlerService.java : {} errors in document ",
            violations.size());
        return false;
    }
}
