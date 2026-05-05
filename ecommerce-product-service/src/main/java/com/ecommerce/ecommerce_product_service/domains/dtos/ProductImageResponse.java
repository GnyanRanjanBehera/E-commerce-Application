package com.ecommerce.ecommerce_product_service.domains.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductImageResponse {
    private Long id;
    private String imageUrl;
}
