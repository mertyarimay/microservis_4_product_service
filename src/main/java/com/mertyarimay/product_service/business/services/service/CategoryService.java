package com.mertyarimay.product_service.business.services.service;

import com.mertyarimay.product_service.business.dto.categoryDto.CreateCategoryDto;
import com.mertyarimay.product_service.business.dto.categoryDto.GetAllCategoryDto;
import com.mertyarimay.product_service.business.dto.categoryDto.GetAllCategoryTitleIdDto;
import com.mertyarimay.product_service.business.dto.categoryDto.GetByIdCategoryDto;

import java.util.List;
import java.util.Optional;


public interface CategoryService {

    CreateCategoryDto create(CreateCategoryDto createCategoryDto);
    List<GetAllCategoryTitleIdDto> getAll(Optional<Integer>categoryTitleId);
    GetByIdCategoryDto getById(int id);
    List<GetAllCategoryDto>getAllCategory();

    boolean delete(int id);
}
