package com.ecommerce.ecommerce_product_service.services.impl;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductImageRequest;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductImageResponse;
import com.ecommerce.ecommerce_product_service.domains.enities.ProductImage;
import com.ecommerce.ecommerce_product_service.domains.enities.ProductVariant;
import com.ecommerce.ecommerce_product_service.repositories.ProductImageRepo;
import com.ecommerce.ecommerce_product_service.repositories.ProductVariantRepo;
import com.ecommerce.ecommerce_product_service.services.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private ProductVariantRepo variantRepo;

    private ProductImageRepo imageRepo;

    @Override
    public List<ProductImageResponse> addImages(Long variantId, List<ProductImageRequest> requests) {

        ProductVariant variant = variantRepo.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        List<ProductImage> images = new ArrayList<>();

        for (ProductImageRequest req : requests) {
            ProductImage image = ProductImage.builder()
                    .imageUrl(req.getImageUrl())
                    .variant(variant)
                    .build();

            images.add(image);
        }

        return imageRepo.saveAll(images)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ProductImageResponse> getImages(Long variantId) {
        return imageRepo.findByVariantId(variantId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProductImageResponse mapToResponse(ProductImage image) {
        return ProductImageResponse.builder()
                .id(image.getId())
                .imageUrl(image.getImageUrl())
                .build();
    }
}
