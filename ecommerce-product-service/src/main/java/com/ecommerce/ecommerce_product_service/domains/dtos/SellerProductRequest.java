package com.ecommerce.ecommerce_product_service.domains.dtos;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerProductRequest {

        private Long sellerId;
        private Long variantId;

        private BigDecimal price;
        private Integer stock;

}
