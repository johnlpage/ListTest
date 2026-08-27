package com.johnlpage.memex.Listing.repository;

import com.johnlpage.memex.Listing.model.Listing;
import com.johnlpage.memex.generics.repository.MongoHistoryRepository;
import com.johnlpage.memex.generics.repository.OptimizedMongoDownstreamRepository;
import com.johnlpage.memex.generics.repository.OptimizedMongoLoadRepository;
import com.johnlpage.memex.generics.repository.OptimizedMongoQueryRepository;
import java.util.stream.Stream;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/*
 * Repository for Listing entity
 * Defines simple operations by name of function or by JavaScript versions of MongoDB queries
 */
@Repository
public interface ListingRepository
    extends MongoRepository<Listing, Long>,
        OptimizedMongoLoadRepository<Listing>,
        OptimizedMongoQueryRepository<Listing>,
        OptimizedMongoDownstreamRepository<Listing>,
        MongoHistoryRepository<Listing, Long> {


/*
 * =====================================================================
 * SPRING DATA MONGODB REPOSITORY QUERY EXAMPLES
 * =====================================================================
 *
 * These examples demonstrate the various ways to define queries in a
 * Spring Data MongoDB repository interface. Replace the placeholder
 * names with your actual field names and types.
 *
 * Terminology:
 *   - FieldA, FieldB      : Top-level fields on the document
 *   - SubObject            : A nested/embedded document
 *   - SubObject.FieldA     : A field within the nested document
 *
 * ---------------------------------------------------------------------
 * 1. AUTOMATICALLY DERIVED QUERIES
 * ---------------------------------------------------------------------
 * Spring Data parses the method name to generate the query.
 *
 *   // Find where a top-level numeric field exceeds a threshold
 *   List<MyDocument> findByFieldAGreaterThan(Long value);
 *
 *   // Find by two fields within a nested subdocument (AND)
 *   List<MyDocument> findBySubObjectFieldAAndSubObjectFieldB(
 *       String fieldAValue, String fieldBValue);
 *
 * ---------------------------------------------------------------------
 * 2. ANNOTATION-BASED AGGREGATION
 * ---------------------------------------------------------------------
 * Use @Aggregation for pipelines such as $group, $project, $unwind.
 * Returns Document when the result shape differs from the entity.
 *
 *   @Aggregation(pipeline = {
 *       "{ '$match':  { 'subObject.fieldA': ?0 } }",
 *       "{ '$group':  { '_id': null, 'averageFieldB': { '$avg': '$subObject.fieldB' } } }"
 *   })
 *   List<Document> findAverageFieldBByFieldA(String fieldAValue);
 *
 * ---------------------------------------------------------------------
 * 3. ANNOTATION-BASED UPDATE (WITHOUT FULL DOCUMENT READ/WRITE)
 * ---------------------------------------------------------------------
 * Combines @Query to match and @Update to modify in place.
 * Efficient for targeted field updates; bypasses entity serialisation.
 *
 *   @Query("{ 'fieldA': ?0 }")
 *   @Update("{ '$inc': { 'fieldB': ?1 } }")
 *   void adjustFieldB(Long fieldAValue, int increment);
 *
 * ---------------------------------------------------------------------
 * 4. @Query WITH MONGODB QUERY LANGUAGE (MQL)
 * ---------------------------------------------------------------------
 * Gives access to the full range of MQL operators while still
 * binding method parameters positionally.
 *
 *   @Query("{ 'subObject.fieldA': ?0, 'subObject.fieldB': ?1 }")
 *   List<MyDocument> findBySubObjectFieldAAndFieldB(
 *       String fieldAValue, String fieldBValue);
 *
 * =====================================================================
 */

    // Streaming version for large result sets
    Stream<Listing> findAllBy();
}
