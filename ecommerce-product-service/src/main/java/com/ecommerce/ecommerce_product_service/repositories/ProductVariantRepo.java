package com.ecommerce.ecommerce_product_service.repositories;
import com.ecommerce.ecommerce_product_service.domains.enities.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepo extends JpaRepository<ProductVariant,Long> {

}
