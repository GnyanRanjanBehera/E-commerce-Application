package com.ecommerce.ecommerce_product_service.controllers;

import com.ecommerce.ecommerce_product_service.domains.dtos.ProductImageRequest;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductImageResponse;
import com.ecommerce.ecommerce_product_service.services.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/variant-image")
public class VariantImageController {

    private final ProductImageService imageService;

    @PostMapping
    public ResponseEntity<List<ProductImageResponse>> addImages(
            @PathVariable Long variantId,
            @RequestBody List<ProductImageRequest> requests) {

        return ResponseEntity.ok(
                imageService.addImages(variantId, requests)
        );
    }

    @GetMapping
    public ResponseEntity<List<ProductImageResponse>> getImages(
            @PathVariable Long variantId) {

        return ResponseEntity.ok(
                imageService.getImages(variantId)
        );
    }
}
