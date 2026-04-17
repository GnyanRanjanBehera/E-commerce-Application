package com.ecommerce.ecommerce_product_service.domains.dtos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReviewDto {

    private Long id;

    private Integer rating;

    private String comment;

    private ProductDto product;
}
