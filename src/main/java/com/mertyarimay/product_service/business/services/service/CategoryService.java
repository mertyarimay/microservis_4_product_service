package com.mertyarimay.product_service.business.services.service;

import com.mertyarimay.product_service.business.dto.categoryDto.CreateCategoryDto;



public interface CategoryService {

    CreateCategoryDto create(CreateCategoryDto createCategoryDto);
}
