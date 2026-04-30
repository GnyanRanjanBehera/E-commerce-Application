package com.ecommerce.ecommerce_product_service.domains.dtos;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantRequest {

    private String sku;
    private String color;
    private String size;
}
