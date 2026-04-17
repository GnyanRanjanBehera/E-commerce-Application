package com.ecommerce.ecommerce_product_service.domains.dtos;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDto {

    private Long id;

    private String imageUrl;

    private ProductVariantDto variant;
}
