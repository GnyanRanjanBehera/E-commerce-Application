package com.ecommerce.ecommerce_product_service.domains.dtos;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateVariantRequest {
    private String color;
    private String size;
}
