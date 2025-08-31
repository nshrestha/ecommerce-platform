package com.programming.services.service;

import com.programming.services.dto.ProductRequest;
import com.programming.services.dto.ProductResponse;
import com.programming.services.model.Product;
import com.programming.services.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
       Product product = Product.builder()
               .name(productRequest.getName())
               .description(productRequest.getDescription())
               .price(productRequest.getPrice())
               .sku(productRequest.getSku())
               .category(productRequest.getCategory())
               .build();

      productRepository.save(product);
      log.info("Product {} is created and saved", product.getId());
    }

    
 /*   public Page<Product> getAllProducts(ProductResponse productResponse) {
       productRepository.findAll();
    }
*/
    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> searchProducts(String query) {
        return null;
    }



    public Product updateProduct(String id, Product product) {
        return null;
    }

    public List<Product> getProductsByPriceRange(BigDecimal min, BigDecimal max) {
        return null;
    }

    public void deleteProduct(String id) {

    }


    public List<ProductResponse> getAllProducts() {
       List<Product> products = productRepository.findAll();

      return products.stream()
               .map(this::mapToProductResponse)
               .toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .sku(product.getSku())
                .category(product.getCategory())
                .build();
    }
}
