package com.mertyarimay.product_service.business.services.impl;


import com.mertyarimay.product_service.business.dto.productBrandDto.CreateProductBrandDto;
import com.mertyarimay.product_service.business.dto.productBrandDto.GetAllProductBrandDto;
import com.mertyarimay.product_service.business.dto.productBrandDto.GetByIdProductBrandDto;
import com.mertyarimay.product_service.business.services.service.ProductBrandService;
import com.mertyarimay.product_service.business.services.servicesRules.ProductBrandServicesRules;
import com.mertyarimay.product_service.data.entity.CategoryEntity;
import com.mertyarimay.product_service.data.entity.ProductBrandEntity;
import com.mertyarimay.product_service.data.repository.ICategoryRepository;
import com.mertyarimay.product_service.data.repository.IProductBrandRepository;
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
public class ProductBrandServiceImpl implements ProductBrandService {
    private final ModelMapperService modelMapperService;
    private final IProductBrandRepository productBrandRepository;
    private final ICategoryRepository categoryRepository;
    private final ProductBrandServicesRules productBrandServicesRules;


    @Override
    @Transactional
    public CreateProductBrandDto create(CreateProductBrandDto createProductBrandDto) {
       if(createProductBrandDto!=null){
           CategoryEntity categoryEntity=categoryRepository.findById(createProductBrandDto.getCategoryId()).orElse(null);
           if(categoryEntity!=null){
               ProductBrandEntity productBrandEntity=modelMapperService.forRequest().map(createProductBrandDto,ProductBrandEntity.class);
               productBrandEntity.setCategoryEntity(categoryEntity);
               productBrandRepository.save(productBrandEntity);

               CreateProductBrandDto createProductBrand=modelMapperService.forRequest().map(productBrandEntity,CreateProductBrandDto.class);
               return createProductBrand;
           }else {
               throw new BusinessException("Girdiğiniz CategoryId Mevcut değil");

           }
       }
        return null;


    }

    @Override
    public List<GetAllProductBrandDto> getAll(Optional<Integer> categoryId) {
        if(categoryId.isPresent()){
            productBrandServicesRules.checkCategoryId(categoryId.get());
            List<ProductBrandEntity>productBrandEntities=productBrandRepository.findByCategoryEntity_Id(categoryId.get());
            List<GetAllProductBrandDto>getAllProductBrandDtos=productBrandEntities.stream().map(productBrandEntity -> {
                GetAllProductBrandDto getAllProductBrandDto=new GetAllProductBrandDto();
                getAllProductBrandDto.setBrandName(productBrandEntity.getBrandName());
                getAllProductBrandDto.setCategoryName(productBrandEntity.getCategoryEntity().getCategoryName());
                List<String>productList=productBrandEntity.getProductEntities().stream().map(productEntity -> productEntity.getProductName()).collect(Collectors.toList());
                getAllProductBrandDto.setProductList(productList);
                return getAllProductBrandDto;
            }).collect(Collectors.toList());

            return getAllProductBrandDtos;
        }
        else {
            return null;
        }
    }

    @Override
    public GetByIdProductBrandDto getById(int id) {
        ProductBrandEntity productBrandEntity=productBrandRepository.findById(id).orElse(null);
        if(productBrandEntity!=null){
            GetByIdProductBrandDto getByIdProductBrandDto=new GetByIdProductBrandDto();
            getByIdProductBrandDto.setCategoryName(productBrandEntity.getCategoryEntity().getCategoryName());
            getByIdProductBrandDto.setBrandName(productBrandEntity.getBrandName());
            List<String>productList=productBrandEntity.getProductEntities().stream().map(productEntity -> productEntity.getProductName()).collect(Collectors.toList());
            getByIdProductBrandDto.setProductList(productList);
            return getByIdProductBrandDto;
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        ProductBrandEntity productBrandEntity=productBrandRepository.findById(id).orElse(null);
        if(productBrandEntity!=null){
            productBrandRepository.deleteById(id);
            if (!productBrandRepository.existsById(id))
            return true;
        }
        return false;
    }


}

