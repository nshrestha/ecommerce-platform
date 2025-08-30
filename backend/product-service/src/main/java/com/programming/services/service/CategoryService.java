package com.programming.services.service;

import com.programming.services.exceptions.CategoryNotFoundException;
import com.programming.services.model.Category;
import com.programming.services.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MongoTemplate mongoTemplate;

    // Get entire category tree
    public List<Category> getCategoryTree() {
        List<Category> rootCategories = categoryRepository.findByParentIsNullAndActiveTrue();

        return rootCategories.stream()
                .map(this::populateChildren)
                .collect(Collectors.toList());
    }

    // Recursively populate children
    private Category populateChildren(Category category) {
        List<Category> children = categoryRepository.findByParentIdAndActiveTrue(category.getId());
      /*  if (children != null && !children.isEmpty()) {
            category.setChildren(children.stream()
                    .map(this::populateChildren)
                    .collect(Collectors.toList()));
        }*/
        return category;
    }

    // Get all descendants of a category
    public List<Category> getCategoryDescendants(String categoryId) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        return getDescendants(category);
    }

    private List<Category> getDescendants(Category category) {
        List<Category> descendants = new ArrayList<>();
        List<Category> children = categoryRepository.findByParentIdAndActiveTrue(category.getId());

        for (Category child : children) {
            descendants.add(child);
            descendants.addAll(getDescendants(child));
        }

        return descendants;
    }

    // Update category hierarchy (ancestors field)
    @Transactional
    public Category updateCategoryHierarchy(String categoryId, String newParentId) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        Category newParent = newParentId != null ?
                categoryRepository.findById(newParentId)
                        .orElseThrow(() -> new CategoryNotFoundException(newParentId)) : null;

        category.setParent(newParent);
        updateAncestors(category, newParent);

        return categoryRepository.save(category);
    }

    private void updateAncestors(Category category, Category parent) {
        if (parent == null) {
            category.setAncestors(new ArrayList<>());
        } else {
            List<String> ancestors = new ArrayList<>(parent.getAncestors());
            ancestors.add(parent.getName());
            category.setAncestors(ancestors);
        }

        // Update all descendants
        updateDescendantsAncestors(category);
    }

    private void updateDescendantsAncestors(Category category) {
        List<Category> children = categoryRepository.findByParentId(category.getId());
        for (Category child : children) {
            List<String> ancestors = new ArrayList<>(category.getAncestors());
            ancestors.add(category.getName());
            child.setAncestors(ancestors);
            categoryRepository.save(child);
            updateDescendantsAncestors(child);
        }
    }

    // Bulk update category active status
    @Transactional
    public void bulkUpdateActiveStatus(List<String> categoryIds, boolean active) {
     //   Query query = Query.query(Criteria.where("_id").in(categoryIds));
      //  Update update = new Update().set("active", active).set("updatedAt", LocalDateTime.now());
       // mongoTemplate.updateMulti(query, update, Category.class);
    }

    // Search categories with multiple criteria
    public Page<Category> searchCategories(String name, Boolean active, List<String> ancestors,
                                           Pageable pageable) {
        Criteria criteria = new Criteria();

        if (StringUtils.hasText(name)) {
            criteria.and("name").regex(name, "i");
        }

        if (active != null) {
            criteria.and("active").is(active);
        }

        if (ancestors != null && !ancestors.isEmpty()) {
            criteria.and("ancestors").in(ancestors);
        }

        /*Query query = new Query(criteria).with(pageable);
        long count = mongoTemplate.count(query, Category.class);
        List<Category> categories = mongoTemplate.find(query, Category.class);
*/
      //  return new PageImpl<>(categories, pageable, count);
        return null;
    }
}
