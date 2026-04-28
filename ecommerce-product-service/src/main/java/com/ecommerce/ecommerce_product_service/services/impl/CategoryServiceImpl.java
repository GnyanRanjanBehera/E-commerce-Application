package com.ecommerce.ecommerce_product_service.services.impl;

import com.ecommerce.ecommerce_product_service.domains.dtos.CategoryDto;
import com.ecommerce.ecommerce_product_service.domains.dtos.PageableResponse;
import com.ecommerce.ecommerce_product_service.domains.enities.Category;
import com.ecommerce.ecommerce_product_service.exceptions.BadApiRequestException;
import com.ecommerce.ecommerce_product_service.exceptions.ResourceNotFoundException;
import com.ecommerce.ecommerce_product_service.repositories.CategoryRepo;
import com.ecommerce.ecommerce_product_service.services.CategoryService;
import com.ecommerce.ecommerce_product_service.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public CategoryDto updateCategory(Long cateId, String name, Long parentId, Boolean isActive) {
        Category category = categoryRepo.findById(cateId).orElseThrow(() -> new ResourceNotFoundException("category not found"));
        if(name != null && !name.trim().isEmpty()){
            category.setName(name);
        }

        if(parentId!=null){
            Category parent = categoryRepo.findById(parentId).orElseThrow(() -> new ResourceNotFoundException("you parent id not found"));
            category.setParent(parent);
        }
        if (isActive != null) {
            category.setIsActive(isActive);
        }
        Category saveCategory = categoryRepo.save(category);
        return mapper.map(saveCategory, CategoryDto.class);
    }

    @Override
    public PageableResponse<CategoryDto> fetchCategories(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> all = categoryRepo.findAll(pageable);
        return Helper.getPageableResponse(all,CategoryDto.class);

    }
}
