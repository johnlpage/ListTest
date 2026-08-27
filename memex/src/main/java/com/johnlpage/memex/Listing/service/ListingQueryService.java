package com.johnlpage.memex.Listing.service;

import com.johnlpage.memex.Listing.model.Listing;
import com.johnlpage.memex.Listing.repository.ListingRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;


// This is just Layering Glue, Business logic goes here not direct database access
// That goes via the Repository, you may choose to add logging here for example

@Service
public class ListingQueryService {

    public static final Class<Listing> MODELCLASS = Listing.class;
    private static final Logger LOG = LoggerFactory.getLogger(ListingQueryService.class);
    private final ListingRepository repository;

    public ListingQueryService(ListingRepository repository) {
        this.repository = repository;
    }

    public List<Listing> mongoDbNativeQuery(String jsonString) {
        int cost = repository.costMongoDbNativeQuery(jsonString, MODELCLASS);
        LOG.info("Query cost is {}, running anyway. ", cost);

        // You could take various approaches here, allow some, deny some, send "bad" queries to
        // secondaries. Perhaps even enforce additional limits of query clauses - COLLSCANs sorted
        // reverse by a date field and limited for example

        return repository.mongoDbNativeQuery(jsonString, MODELCLASS);
    }

    public List<Listing> atlasSearchQuery(String jsonString) {
        return repository.atlasSearchQuery(jsonString, MODELCLASS);
    }

    public Optional<Listing> getById(Long id) {
        return repository.findById(id);
    }

    public Slice<Listing> getByExample(Listing probe, int page, int size) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<Listing> example = Example.of(probe, matcher);
        return repository.findAll(example, PageRequest.of(page, size));
    }
}
