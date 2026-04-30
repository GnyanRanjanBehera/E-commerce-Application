package com.ecommerce.ecommerce_product_service.domains.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantResponse {

    private Long id;

    private String sku;

    private String color;

    private String size;
}
