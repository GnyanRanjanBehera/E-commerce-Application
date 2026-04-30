package com.ecommerce.ecommerce_product_service.services;
import com.ecommerce.ecommerce_product_service.domains.dtos.PageableResponse;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductDto;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductRequest;

public interface ProductService {
    public ProductDto createProduct(ProductRequest productDto);

    public ProductDto getProduct(Long productId);

    public ProductDto updateProduct(ProductRequest productDto);

    public void deleteProduct(Long id);

    public PageableResponse<ProductDto> getAllProduct();

    public PageableResponse<ProductDto> getByCategory(Long categoryId);
}
