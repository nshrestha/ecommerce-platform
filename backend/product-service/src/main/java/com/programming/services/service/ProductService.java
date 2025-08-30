package com.programming.services.service;

import com.programming.services.model.Product;
import com.programming.services.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public Page<Product> getAllProducts(Pageable pageable) {
       return    productRepository.findAll(pageable);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> searchProducts(String query) {
        return null;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        return null;
    }

    public List<Product> getProductsByPriceRange(BigDecimal min, BigDecimal max) {
        return null;
    }

    public void deleteProduct(String id) {

    }



}
