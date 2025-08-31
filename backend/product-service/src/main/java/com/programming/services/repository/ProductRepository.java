package com.programming.services.repository;

import com.programming.services.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findBySku(String sku);

  //  Page<Product> findByCategoriesContaining(String categoryId, Pageable pageable);

    Optional<Product> findProductById(String is);

    List<Product> findByCategory(String category);

    List<Product> findByActiveTrue();

  /*  @Query("{'price': {$gte: ?0, $lte: ?1}}")
    @Modifying
    List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    @Modifying
    List<Product> searchByName(String name);

    @Query("{ 'attributes': { $elemMatch: { 'key': ?0, 'value': ?1 } } }")
    @Modifying
    List<Product> findByAttribute(String key, String value);

    @Query("{ 'averageRating': { $gte: ?0 } }")
    @Modifying
    Page<Product> findByMinRating(Double minRating, Pageable pageable);

    @Modifying
    @Query("{" +
            "  '_id' : ?0 " +
            "}")
    @Update("{" +
            "  '$set' : { " +
            "    'averageRating' : ?1, " +
            "    'reviewCount' : ?2 " +
            "  }" +
            "}")
    void updateRating(String productId, Double averageRating, Integer reviewCount);*/
}
