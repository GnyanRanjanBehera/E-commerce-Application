package com.ecommerce.ecommerce_product_service.controllers;

import com.ecommerce.ecommerce_product_service.domains.dtos.ApiResponseMessage;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductDto;
import com.ecommerce.ecommerce_product_service.domains.dtos.ProductRequest;
import com.ecommerce.ecommerce_product_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private  final ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductRequest productDto){
        ProductDto product = productService.createProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PutMapping("/updateProduct")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductRequest productDto){
        ProductDto product = productService.updateProduct(productDto);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping("/getProduct")
    public ResponseEntity<ProductDto> getProduct(@RequestParam(value = "productId") Long productId){
        ProductDto product = productService.getProduct(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<ApiResponseMessage> deleteProduct(@RequestParam(value = "productId") Long productId){
        productService.deleteProduct(productId);
        ApiResponseMessage message = ApiResponseMessage.builder().message("product Deleted successfully").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }




}
