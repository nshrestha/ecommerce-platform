package com.programming.services.repository;


import com.programming.services.model.ProductReview;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<ProductReview, String> {
    List<ProductReview> findByProductId(String productId);

    List<ProductReview> findByProductIdOrderByCreatedAtDesc(String productId);

    List<ProductReview> findByUserId(String userId);

    @Aggregation(pipeline = {
            "{ $match: { 'product._id': ?0 } }",
            "{ $group: { _id: '$product._id', averageRating: { $avg: '$rating' }, reviewCount: { $sum: 1 } } }"
    })
    RatingStats calculateRatingStats(String productId);

    public interface RatingStats {
        String getId();
        Double getAverageRating();
        Integer getReviewCount();
    }

}
