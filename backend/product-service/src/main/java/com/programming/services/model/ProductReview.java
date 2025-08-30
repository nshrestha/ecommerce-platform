package com.programming.services.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductReview {
    @Id
    private String id;

    @DBRef
    private Product product; // Reference to product

    private String userId;
    private String userName;
    private Integer rating;
    private String title;
    private String comment;
    private Boolean verifiedPurchase;
    private List<String> helpfulVotes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
