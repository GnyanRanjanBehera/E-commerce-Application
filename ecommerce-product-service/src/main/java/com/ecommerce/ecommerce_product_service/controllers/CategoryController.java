package com.ecommerce.ecommerce_product_service.controllers;


import com.ecommerce.ecommerce_product_service.domains.dtos.CategoryDto;
import com.ecommerce.ecommerce_product_service.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryService categoryService;


    @PostMapping("/addCategory")
    public ResponseEntity<CategoryDto> addCategory(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "parentId") Long parentId
    ){
        CategoryDto categoryDto = categoryService.addCategory(name, parentId);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping("/fetchCategoryById/{cateId}")
    public ResponseEntity<CategoryDto> fetchCategoryById(
            @PathVariable(value = "cateId") Long cateId
    ){
        CategoryDto categoryDto = categoryService.fetchCategoryById(cateId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);

    }
}
