package com.mertyarimay.product_service.business.services.impl;

import com.mertyarimay.product_service.business.dto.categoryTitleDto.CreateCategoryTitleDto;
import com.mertyarimay.product_service.business.dto.categoryTitleDto.GetAllCategoryTitleDto;
import com.mertyarimay.product_service.business.dto.categoryTitleDto.GetByIdCategoryTitleDto;
import com.mertyarimay.product_service.business.services.service.CategoryTitleService;
import com.mertyarimay.product_service.data.entity.CategoryTitleEntity;
import com.mertyarimay.product_service.data.repository.ICategoryTitleRepository;
import com.mertyarimay.product_service.mappers.ModelMapperService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<GetAllCategoryTitleDto> getAll() {
       List<CategoryTitleEntity>categoryTitleEntities=categoryTitleRepository.findAll();
       List<GetAllCategoryTitleDto> getAllCategoryTitleDtos =categoryTitleEntities.stream()
               .map(categoryTitleEntity -> modelMapperService.forResponse()
               .map(categoryTitleEntity, GetAllCategoryTitleDto.class)).collect(Collectors.toList());
       return getAllCategoryTitleDtos;
    }

    @Override
    public GetByIdCategoryTitleDto getById(int id) {
      CategoryTitleEntity categoryTitleEntity=categoryTitleRepository.findById(id).orElse(null);
      if(categoryTitleEntity!=null){
          GetByIdCategoryTitleDto getByIdCategoryTitleDto=new GetByIdCategoryTitleDto();
          getByIdCategoryTitleDto.setCategoryTitleName(categoryTitleEntity.getCategoryTitleName());

          List<String>categoryList=categoryTitleEntity.getCategoryEntities().stream()
                  .map(categoryEntity -> categoryEntity.getCategoryName()).collect(Collectors.toList());
          getByIdCategoryTitleDto.setCategoryList(categoryList);

          List<String>productBrandList=categoryTitleEntity.getCategoryEntities().stream()
                  .flatMap(categoryEntity -> categoryEntity.getProductBrandEntities().stream())
                  .map(productBrandEntity -> productBrandEntity.getBrandName()).collect(Collectors.toList());
          getByIdCategoryTitleDto.setProductBrandList(productBrandList);

          List<String>productList=categoryTitleEntity.getCategoryEntities().stream()
                  .flatMap(categoryEntity -> categoryEntity.getProductBrandEntities().stream())
                  .flatMap(productBrandEntity -> productBrandEntity.getProductEntities().stream())
                  .map(productEntity -> productEntity.getProductName()).collect(Collectors.toList());

          getByIdCategoryTitleDto.setProductList(productList);

          return getByIdCategoryTitleDto;
      }
      return null;
    }

    @Override
    public boolean delete(int id) {
        CategoryTitleEntity categoryTitleEntity=categoryTitleRepository.findById(id).orElse(null);
        if(categoryTitleEntity!=null){
            categoryTitleRepository.deleteById(id);
            return true;

        }else {
            return false;
        }
    }

}
