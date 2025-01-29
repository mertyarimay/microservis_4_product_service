package com.mertyarimay.product_service.business.services.service;

import com.mertyarimay.product_service.business.dto.categoryTitleDto.CreateCategoryTitleDto;
import com.mertyarimay.product_service.business.dto.categoryTitleDto.GetAllCategoryTitleDto;
import com.mertyarimay.product_service.business.dto.categoryTitleDto.GetByIdCategoryTitleDto;

import java.util.List;


public interface CategoryTitleService {

    CreateCategoryTitleDto create(CreateCategoryTitleDto createCategoryTitleDto);
    List<GetAllCategoryTitleDto> getAll();
    GetByIdCategoryTitleDto getById(int id);
    boolean delete(int id);
}
