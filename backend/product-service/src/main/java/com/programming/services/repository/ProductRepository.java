package com.programming.services.repository;

import com.programming.services.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findBySku(String sku);

    Optional<Product> findProductById(String is);

    List<Product> findByCategory(String category);

    List<Product> findByActiveTrue();

    @Query("{'price': {$gte: ?0, $lte: ?1}}")
    List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<Product> searchByName(String name);
}
