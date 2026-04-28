package com.ecommerce.ecommerce_product_service.services.impl;
import com.ecommerce.ecommerce_product_service.domains.dtos.PageableResponse;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductDto;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductRequest;
import com.ecommerce.ecommerce_product_service.domains.enities.Category;
import com.ecommerce.ecommerce_product_service.domains.enities.Product;
import com.ecommerce.ecommerce_product_service.exceptions.BadApiRequestException;
import com.ecommerce.ecommerce_product_service.repositories.CategoryRepo;
import com.ecommerce.ecommerce_product_service.repositories.ProductRepo;
import com.ecommerce.ecommerce_product_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    private final CategoryRepo categoryRepo;

    private final ModelMapper mapper;


    @Override
    public ProductDto createProduct(ProductRequest productDto) {
        Category category = categoryRepo.findById(productDto.getCategoryId()).orElseThrow(() -> new BadApiRequestException("category not found"));
        Product product = Product.builder().name(productDto.getName())
                .description(productDto.getDescription())
                .category(category).build();
        Product saveProduct = productRepo.save(product);
        return mapper.map(saveProduct,ProductDto.class);
    }

    @Override
    public ProductDto getProduct(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new BadApiRequestException("product not found"));
        return mapper.map(product,ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductRequest productDto) {
        Product product = productRepo.findById(productDto.getProdId()).orElseThrow(() -> new BadApiRequestException("product not found"));
        if(!productDto.getDescription().isEmpty()){
            product.setDescription(productDto.getDescription());
        }
        if(!productDto.getName().isEmpty()){
            product.setDescription(productDto.getName());
        }

        if(productDto.getCategoryId()!=null){
            Category category = categoryRepo.findById(productDto.getCategoryId()).orElseThrow(() -> new BadApiRequestException("category not found"));
            product.setCategory(category);
        }
        return mapper.map(product,ProductDto.class);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new BadApiRequestException("product not found"));
        productRepo.delete(product);
    }

    @Override
    public PageableResponse<ProductDto> getAllProduct() {
        return null;
    }

    @Override
    public PageableResponse<ProductDto> getByCategory(Long categoryId) {
        return null;
    }
}
