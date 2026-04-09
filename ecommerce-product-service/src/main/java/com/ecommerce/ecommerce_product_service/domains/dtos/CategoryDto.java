package com.ecommerce.ecommerce_product_service.domains.dtos;

import com.ecommerce.ecommerce_product_service.domains.enities.Category;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Long cateId;
    private String name;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CategoryDto parent;
}
