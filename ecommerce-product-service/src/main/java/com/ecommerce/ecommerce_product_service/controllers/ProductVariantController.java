package com.ecommerce.ecommerce_product_service.controllers;
import com.ecommerce.ecommerce_product_service.domains.dtos.ApiResponseMessage;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductVariantRequest;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductVariantResponse;
import com.ecommerce.ecommerce_product_service.domains.dtos.UpdateVariantRequest;
import com.ecommerce.ecommerce_product_service.services.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productvariant")
public class ProductVariantController {
    private  final ProductVariantService variantService;

    @PostMapping("/createVariants")
    public ResponseEntity<List<ProductVariantResponse>> createVariants(
            @RequestParam(value = "productId") Long productId,
            @RequestBody List<ProductVariantRequest> productVariantRequests
    ){


        List<ProductVariantResponse> variants = variantService.createVariants(productId, productVariantRequests);
        return new ResponseEntity<>(variants, HttpStatus.OK);
    }
    @PutMapping("/updateVariant")
    public  ResponseEntity<ProductVariantResponse> updateVariant(
            @RequestParam(value = "productId") Long productId,
            @RequestBody UpdateVariantRequest variantRequest
            ){
        ProductVariantResponse productVariantResponse = variantService.updateVariant(productId, variantRequest);

        return new ResponseEntity<>(productVariantResponse, HttpStatus.OK);

    }
    @DeleteMapping("/deleteVariant")
    public  ResponseEntity<ApiResponseMessage> deleteVariant(
            @RequestParam(value = "productId") Long variantId
            ){
         variantService.deleteVariant(variantId);
        ApiResponseMessage deletedSuccessfully = ApiResponseMessage.builder().success(true).message("Deleted successfully").status(HttpStatus.OK).build();
        return new ResponseEntity<>(deletedSuccessfully, HttpStatus.OK);
    }

    @GetMapping("/getVariantsByProduct")
    public  ResponseEntity<List<ProductVariantResponse>> getVariantsByProduct(
            @RequestParam(value = "productId") Long productId
            ){
        List<ProductVariantResponse> variantsByProduct = variantService.getVariantsByProduct(productId);
        return new ResponseEntity<>(variantsByProduct, HttpStatus.OK);
    }


}
