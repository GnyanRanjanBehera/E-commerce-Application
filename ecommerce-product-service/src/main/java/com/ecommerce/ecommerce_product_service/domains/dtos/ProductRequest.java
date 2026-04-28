package com.ecommerce.ecommerce_product_service.domains.dtos;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    private Long prodId;

    private String name;

    private String description;

    private Long categoryId;
}
