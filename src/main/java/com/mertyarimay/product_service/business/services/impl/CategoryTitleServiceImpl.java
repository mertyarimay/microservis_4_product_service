package com.mertyarimay.product_service.business.services.impl;

import com.mertyarimay.product_service.business.dto.categoryTitleDto.CreateCategoryTitleDto;
import com.mertyarimay.product_service.business.services.service.CategoryTitleService;
import com.mertyarimay.product_service.data.entity.CategoryTitleEntity;
import com.mertyarimay.product_service.data.repository.ICategoryTitleRepository;
import com.mertyarimay.product_service.mappers.ModelMapperService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class CategoryTitleServiceImpl implements CategoryTitleService {
    private final ModelMapperService modelMapperService;
    private final ICategoryTitleRepository categoryTitleRepository;


    @Override
    @Transactional
    public CreateCategoryTitleDto create(CreateCategoryTitleDto createCategoryTitleDto) {
        if (createCategoryTitleDto != null) {
            CategoryTitleEntity categoryTitleEntity = modelMapperService.forRequest().map(createCategoryTitleDto, CategoryTitleEntity.class);
            categoryTitleRepository.save(categoryTitleEntity);
            CreateCategoryTitleDto createCategoryTitle = modelMapperService.forRequest().map(categoryTitleEntity, CreateCategoryTitleDto.class);
            return createCategoryTitle;


        }
        return null;
    }

}
