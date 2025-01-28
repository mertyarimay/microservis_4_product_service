package com.mertyarimay.product_service.business.services.impl;

import com.mertyarimay.product_service.business.dto.categoryDto.CreateCategoryDto;
import com.mertyarimay.product_service.business.services.service.CategoryService;
import com.mertyarimay.product_service.data.entity.CategoryEntity;
import com.mertyarimay.product_service.data.entity.CategoryTitleEntity;
import com.mertyarimay.product_service.data.repository.ICategoryRepository;
import com.mertyarimay.product_service.data.repository.ICategoryTitleRepository;
import com.mertyarimay.product_service.exception.BusinessException;
import com.mertyarimay.product_service.mappers.ModelMapperService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategorySeviceImpl implements CategoryService {
    private final ModelMapperService modelMapperService;
    private final ICategoryRepository categoryRepository;
    private final ICategoryTitleRepository categoryTitleRepository;


    @Override
    @Transactional
    public CreateCategoryDto create(CreateCategoryDto createCategoryDto) {
        if (createCategoryDto != null) {
            CategoryTitleEntity categoryTitleEntity=categoryTitleRepository.findById(createCategoryDto.getCategoryTitleId()).orElse(null);
           if(categoryTitleEntity!=null){
               CategoryEntity categoryEntity=modelMapperService.forRequest().map(createCategoryDto,CategoryEntity.class);
               categoryEntity.setCategoryTitleEntity(categoryTitleEntity);
               categoryRepository.save(categoryEntity);
               CreateCategoryDto createCategory=modelMapperService.forRequest().map(categoryEntity,CreateCategoryDto.class);
               return createCategory;
           }else {
               throw new BusinessException("Bu İd ye Ait Course Title Mevcut Değil");

           }
        }
        return null;
    }

}
