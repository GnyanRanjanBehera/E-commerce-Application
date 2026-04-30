package com.ecommerce.ecommerce_product_service.services;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductVariantRequest;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductVariantResponse;
import com.ecommerce.ecommerce_product_service.domains.dtos.UpdateVariantRequest;

import java.util.List;

public interface ProductVariantService {

    public List<ProductVariantResponse> createVariants(Long productId,List<ProductVariantRequest> variantRequests);

    public ProductVariantResponse updateVariant(Long variantId,
                                                UpdateVariantRequest request);
    public List<ProductVariantResponse> getVariantsByProduct(Long productId);

    public void deleteVariant(Long variantId);
}
