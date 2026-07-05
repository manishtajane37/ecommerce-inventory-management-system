package com.example.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Wraps a paginated list of products along with pagination metadata,
 * as required by the pagination requirement:
 * products, currentPage, totalItems, totalPages
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPageResponse {
    private List<ProductResponse> products;
    private int currentPage;
    private long totalItems;
    private int totalPages;
}
