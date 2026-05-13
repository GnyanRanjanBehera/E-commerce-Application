package com.ecommerce.ecommerce_product_service.controllers;
import com.ecommerce.ecommerce_product_service.domains.dtos.CategoryDto;
import com.ecommerce.ecommerce_product_service.domains.dtos.PageableResponse;
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

    @PutMapping("/updateCategory")
    public  ResponseEntity<CategoryDto> updateCategory(
            @RequestParam(value = "cateId",required = false) Long cateId,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "parentId",required = false) Long parentId,
            @RequestParam(value = "isActive",required = false) boolean isActive
    ){
        CategoryDto categoryDto = categoryService.updateCategory(cateId, name, parentId, isActive);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);

    }

    @GetMapping("/fetchCategories")
    public ResponseEntity<PageableResponse<CategoryDto>> fetchCategories(
            @RequestParam(value = "pageNumber",required = false,defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize,
            @RequestParam(value = "sortBy",required = false,defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir",required = false,defaultValue = "asc") String sortDir
    ){
        PageableResponse<CategoryDto> categoryDtoPageableResponse = categoryService.fetchCategories(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(categoryDtoPageableResponse,HttpStatus.OK);
    }
}
