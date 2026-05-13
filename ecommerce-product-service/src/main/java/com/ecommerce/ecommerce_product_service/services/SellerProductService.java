package com.ecommerce.ecommerce_product_service.services;
import com.ecommerce.ecommerce_product_service.domains.dtos.SellerProductDto;
import com.ecommerce.ecommerce_product_service.domains.dtos.SellerProductRequest;
import java.math.BigDecimal;
import java.util.List;

public interface SellerProductService {
    public SellerProductDto create(SellerProductRequest request);
    public List<SellerProductDto> getByVariant(Long variantId);
    public List<SellerProductDto> getBySeller(Long sellerId);
    public SellerProductDto updateStock(Long sellerProductId,Integer stock);
    public SellerProductDto updatePrice(Long sellerProductId, BigDecimal price);
    public void deactivate(Long sellerProductId);
}
