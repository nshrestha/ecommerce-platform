package com.programming.services.model;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "categories")
@Data
public class Category {
    @Id
    private String id;

    private String name;
    private String description;
    private String slug;

    @DBRef
    private Category parent; // Self-reference

    private List<String> ancestors; // Materialized path
    private Integer displayOrder;
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
