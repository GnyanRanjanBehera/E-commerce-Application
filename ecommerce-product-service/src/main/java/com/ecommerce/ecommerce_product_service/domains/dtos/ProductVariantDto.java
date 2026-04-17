package com.ecommerce.ecommerce_product_service.domains.dtos;

import com.ecommerce.ecommerce_product_service.domains.enities.Product;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariantDto {

    private Long id;

    private String sku;

    private String color;

    private String size;

    private Product product;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
