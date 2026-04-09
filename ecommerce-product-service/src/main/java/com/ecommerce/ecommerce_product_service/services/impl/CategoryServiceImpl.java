package com.ecommerce.ecommerce_product_service.services.impl;

import com.ecommerce.ecommerce_product_service.domains.dtos.CategoryDto;
import com.ecommerce.ecommerce_product_service.domains.enities.Category;
import com.ecommerce.ecommerce_product_service.exceptions.BadApiRequestException;
import com.ecommerce.ecommerce_product_service.exceptions.ResourceNotFoundException;
import com.ecommerce.ecommerce_product_service.repositories.CategoryRepo;
import com.ecommerce.ecommerce_product_service.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper mapper;



    @Override
    public CategoryDto addCategory(String name, Long parentId) {

        CategoryDto categoryDto = CategoryDto.builder().name(name)
                .isActive(true)
                .build();
        if(parentId!=null){
            Category parent = categoryRepo.findById(parentId).orElseThrow(() -> new ResourceNotFoundException("you parent id not found"));
            categoryDto.setParent(mapper.map(parent,CategoryDto.class));
        }
        Category categoryMap = mapper.map(categoryDto, Category.class);
        Category saveCategory = categoryRepo.save(categoryMap);
        return mapper.map(saveCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto fetchCategoryById(Long cateId) {
        Category category = categoryRepo.findById(cateId).orElseThrow(BadApiRequestException::new);
        return mapper.map(category, CategoryDto.class);
    }
}
