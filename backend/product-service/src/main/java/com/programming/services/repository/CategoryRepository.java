package com.programming.services.repository;

import com.programming.services.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {

    // Find by slug (unique)
    Optional<Category> findBySlug(String slug);

    // Find categories by name (case-insensitive)
    List<Category> findByNameContainingIgnoreCase(String name);

    // Find active categories
    List<Category> findByActiveTrue();

    // Find categories by display order
    List<Category> findByDisplayOrder(Integer displayOrder);

    // Find categories with pagination
    Page<Category> findAll(Pageable pageable);

    // Find direct children of a parent category
    List<Category> findByParentId(String parentId);

    // Find categories by parent with active status
    List<Category> findByParentIdAndActiveTrue(String parentId);

    // Find all root categories (where parent is null)
    List<Category> findByParentIsNull();

    // Find active root categories
    List<Category> findByParentIsNullAndActiveTrue();

    // Find categories by ancestor path
    @Query("{ 'ancestors': ?0 }")
    List<Category> findByAncestor(String ancestorName);

    // Find categories containing specific ancestor in array
    @Query("{ 'ancestors': { $in: [?0] } }")
    List<Category> findByAncestorsContaining(String ancestor);

    // Find categories with multiple ancestors
    @Query("{ 'ancestors': { $all: ?0 } }")
    List<Category> findByAllAncestors(List<String> ancestors);

    // Find categories by display order range
    List<Category> findByDisplayOrderBetween(Integer start, Integer end);

    // Count categories by active status
    long countByActiveTrue();

    // Check if category exists by slug
    boolean existsBySlug(String slug);

    // Find categories with custom query for complex searches
    @Query("{ '$and': [ { 'active': true }, { 'name': { $regex: ?0, $options: 'i' } } ] }")
    List<Category> findActiveCategoriesByNamePattern(String namePattern);

    // Find categories and sort by display order
    List<Category> findByActiveTrueOrderByDisplayOrderAsc();

    // Find categories by multiple slugs
    List<Category> findBySlugIn(List<String> slugs);
}
