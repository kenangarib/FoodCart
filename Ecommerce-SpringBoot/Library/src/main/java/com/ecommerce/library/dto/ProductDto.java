package com.ecommerce.library.dto;

import com.ecommerce.library.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double costPrice;
    private Double salePrice;
    private Integer currentQuantity;
    private Category category;
    private String image;
    private boolean activated;
    private boolean deleted;
}
