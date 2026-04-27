package com.ecommerce.ecommerce_product_service.services;

public interface ProductService {
    public ProductDto createProduct(ProductDto productDto);

    public ProductDto getProduct(Long productId);

    public ProductDto updateProduct(ProductDto productDto);

    public void deleteProduct(Long id);

    public Page




}
