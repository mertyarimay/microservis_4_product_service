package com.mertyarimay.product_service.business.services.impl;

import com.mertyarimay.product_service.business.dto.categoryDto.CreateCategoryDto;
import com.mertyarimay.product_service.business.dto.categoryDto.GetAllCategoryDto;
import com.mertyarimay.product_service.business.dto.categoryDto.GetAllCategoryTitleIdDto;
import com.mertyarimay.product_service.business.dto.categoryDto.GetByIdCategoryDto;
import com.mertyarimay.product_service.business.services.service.CategoryService;
import com.mertyarimay.product_service.business.services.servicesRules.CategoryServiceRules;
import com.mertyarimay.product_service.data.entity.CategoryEntity;
import com.mertyarimay.product_service.data.entity.CategoryTitleEntity;
import com.mertyarimay.product_service.data.repository.ICategoryRepository;
import com.mertyarimay.product_service.data.repository.ICategoryTitleRepository;
import com.mertyarimay.product_service.exception.BusinessException;
import com.mertyarimay.product_service.mappers.ModelMapperService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategorySeviceImpl implements CategoryService {
    private final ModelMapperService modelMapperService;
    private final ICategoryRepository categoryRepository;
    private final ICategoryTitleRepository categoryTitleRepository;
    private final CategoryServiceRules categoryServiceRules;


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
               throw new BusinessException("Bu İd ye Ait category Title Mevcut Değil");

           }
        }
        return null;
    }
    @Override
    public List<GetAllCategoryTitleIdDto> getAll(Optional<Integer> categoryTitleId) {
        if (categoryTitleId.isPresent()) {
            categoryServiceRules.checkCategoryTitleId(categoryTitleId.get());
            List<CategoryEntity> categoryEntities = categoryRepository.findByCategoryTitleEntity_Id(categoryTitleId.get());
            List<GetAllCategoryTitleIdDto> getAllCategoryTitleIdDtos = categoryEntities.stream().map(categoryEntity -> {
                GetAllCategoryTitleIdDto getAllCategoryTitleIdDto = new GetAllCategoryTitleIdDto();
                getAllCategoryTitleIdDto.setCategoryTitleName(categoryEntity.getCategoryTitleEntity().getCategoryTitleName());
                getAllCategoryTitleIdDto.setCategoryName(categoryEntity.getCategoryName());
                List<String> productBrandList = categoryEntity.getProductBrandEntities().stream()
                        .map(productBrandEntity -> productBrandEntity.getBrandName())
                        .collect(Collectors.toList());
                getAllCategoryTitleIdDto.setProductBrandList(productBrandList);


                List<String> productList = categoryEntity.getProductBrandEntities().stream()
                        .flatMap(productBrandEntity -> productBrandEntity.getProductEntities().stream())
                        .map(productEntity -> productEntity.getProductName())
                        .collect(Collectors.toList());


                getAllCategoryTitleIdDto.setProductList(productList);
                return getAllCategoryTitleIdDto;
            }).collect(Collectors.toList());

            return getAllCategoryTitleIdDtos;

        }
        return null;
    }
    @Override
    public GetByIdCategoryDto getById(int id) {
        CategoryEntity categoryEntity=categoryRepository.findById(id).orElse(null);
        if(categoryEntity!=null){
            GetByIdCategoryDto getByIdCategoryDto=new GetByIdCategoryDto();
            getByIdCategoryDto.setCategoryName(categoryEntity.getCategoryName());

            List<String> productList = categoryEntity.getProductBrandEntities().stream()
                    .flatMap(productBrandEntity -> productBrandEntity.getProductEntities().stream()
                            .map(productEntity -> productEntity.getProductName()))
                    .collect(Collectors.toList());


            getByIdCategoryDto.setProductList(productList);

            return getByIdCategoryDto;
        }
        return null;
    }

    @Override
    public List<GetAllCategoryDto> getAllCategory() {
        List<CategoryEntity>categoryEntities=categoryRepository.findAll();
        List<GetAllCategoryDto>getAllCategoryDtos=categoryEntities.stream()
                .map(categoryEntity -> modelMapperService.forResponse()
                        .map(categoryEntity,GetAllCategoryDto.class))
                .collect(Collectors.toList());
        return getAllCategoryDtos;
    }

    @Override
    public boolean delete(int id) {
        CategoryEntity categoryEntity=categoryRepository.findById(id).orElse(null);
        if(categoryEntity!=null){
            categoryRepository.deleteById(id);
            if(!categoryRepository.existsById(id)){
                return true;
            }

        }
        return false;
    }}
