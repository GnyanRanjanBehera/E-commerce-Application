package com.ecommerce.ecommerce_product_service.services;

import com.ecommerce.ecommerce_product_service.domains.dtos.CategoryDto;
import com.ecommerce.ecommerce_product_service.domains.dtos.PageableResponse;

public interface CategoryService {

    public CategoryDto addCategory(String name,Long parentId);

    public CategoryDto fetchCategoryById(Long cateId);

    public CategoryDto updateCategory(Long cateId,String name,Long parentId,Boolean isActive);

    public PageableResponse<CategoryDto> fetchCategories(int pageNumber,int pageSize,String sortBy,String sortDir);

}
