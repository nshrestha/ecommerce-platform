package com.programming.services.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String sku;
    private String category;

    @DBRef
    private List<Category> categories; // Reference to category documents

    private List<ProductImage> images;
    private List<ProductAttribute> attributes;
    private ProductInventory inventory;
    private ProductShipping shipping;

    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
