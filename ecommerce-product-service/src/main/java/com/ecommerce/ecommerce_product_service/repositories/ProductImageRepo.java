package com.ecommerce.ecommerce_product_service.repositories;

import com.ecommerce.ecommerce_product_service.domains.enities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepo extends JpaRepository<ProductImage,Long> {
    List<ProductImage> findByVariantId(Long variantId);
}
