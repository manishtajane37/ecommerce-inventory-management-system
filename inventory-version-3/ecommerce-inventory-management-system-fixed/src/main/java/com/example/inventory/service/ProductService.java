package com.example.inventory.service;

import com.example.inventory.dto.ProductPageResponse;
import com.example.inventory.dto.ProductRequest;
import com.example.inventory.dto.ProductResponse;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);

    ProductResponse getProductById(Long id);

    /**
     * Returns a paginated and optionally sorted list of products.
     *
     * @param page      zero-based page index
     * @param size      number of items per page
     * @param sortBy    field to sort by ("price" or "name"); may be null for no sorting
     * @param direction "asc" or "desc"
     */
    ProductPageResponse getAllProducts(int page, int size, String sortBy, String direction);
}
