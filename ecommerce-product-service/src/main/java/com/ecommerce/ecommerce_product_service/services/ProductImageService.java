package com.ecommerce.ecommerce_product_service.services;

import com.ecommerce.ecommerce_product_service.domains.dtos.ProductImageRequest;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductImageResponse;

import java.util.List;

public interface ProductImageService {

    public List<ProductImageResponse> addImages(Long variantId,
                                                List<ProductImageRequest> requests);
    public List<ProductImageResponse> getImages(Long variantId);
}
