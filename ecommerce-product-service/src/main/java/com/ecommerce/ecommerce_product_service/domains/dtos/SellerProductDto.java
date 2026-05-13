package com.ecommerce.ecommerce_product_service.domains.dtos;
import com.ecommerce.ecommerce_product_service.domains.enities.ProductVariant;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerProductDto {

    private Long id;

    private Integer sellerId;

    private ProductVariant variant;


    private BigDecimal price;


    private Integer stock;


    private Boolean isActive;
}
