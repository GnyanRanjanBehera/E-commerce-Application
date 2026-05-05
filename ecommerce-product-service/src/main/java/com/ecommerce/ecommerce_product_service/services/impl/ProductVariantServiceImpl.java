package com.ecommerce.ecommerce_product_service.services.impl;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductVariantRequest;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductVariantResponse;
import com.ecommerce.ecommerce_product_service.domains.dtos.UpdateVariantRequest;
import com.ecommerce.ecommerce_product_service.domains.enities.Product;
import com.ecommerce.ecommerce_product_service.domains.enities.ProductVariant;
import com.ecommerce.ecommerce_product_service.exceptions.BadApiRequestException;
import com.ecommerce.ecommerce_product_service.exceptions.ResourceNotFoundException;
import com.ecommerce.ecommerce_product_service.repositories.ProductRepo;
import com.ecommerce.ecommerce_product_service.repositories.ProductVariantRepo;
import com.ecommerce.ecommerce_product_service.services.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductRepo productRepo;

    private final ProductVariantRepo variantRepo;


    @Override
    public List<ProductVariantResponse> createVariants(Long productId, List<ProductVariantRequest> variantRequests) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found"));
        List<ProductVariant> variantList=new ArrayList<>();
       for(ProductVariantRequest req:variantRequests){
           if(variantRepo.existsBySku(req.getSku())){
               throw new BadApiRequestException("SKU already exists"+req.getSku());
           }
           ProductVariant variant = ProductVariant.builder()
                   .sku(req.getSku())
                   .color(req.getColor())
                   .size(req.getSize())
                   .product(product)
                   .build();
           variantList.add(variant);
       }
        return variantRepo.saveAll(variantList)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductVariantResponse updateVariant(Long variantId, UpdateVariantRequest request) {
        ProductVariant variant = variantRepo.findById(variantId)
                .orElseThrow(() -> new ResourceNotFoundException("Variant not found"));
        variant.setColor(request.getColor());
        variant.setSize(request.getSize());
        return mapToResponse(variantRepo.save(variant));
    }

    @Override
    public List<ProductVariantResponse> getVariantsByProduct(Long productId) {
        return variantRepo.findByProductProdId(productId).stream().map(this::mapToResponse).toList();

    }

    @Override
    public void deleteVariant(Long variantId) {
        ProductVariant variant = variantRepo.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));
        variantRepo.delete(variant);
    }

    private ProductVariantResponse mapToResponse(ProductVariant variant) {
        return ProductVariantResponse.builder()
                .id(variant.getId())
                .sku(variant.getSku())
                .color(variant.getColor())
                .size(variant.getSize())
                .build();
    }
}
