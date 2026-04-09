package com.ecommerce.ecommerce_product_service.repositories;
import com.ecommerce.ecommerce_product_service.domains.enities.SellerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerProductRepo extends JpaRepository<SellerProduct,Long> {

}
