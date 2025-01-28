package com.mertyarimay.product_service.business.services.impl;


import com.mertyarimay.product_service.business.dto.productDto.CreateProductDto;
import com.mertyarimay.product_service.business.dto.productDto.GetAllProductDto;
import com.mertyarimay.product_service.business.dto.productDto.GetByIdProductDto;
import com.mertyarimay.product_service.business.dto.productDto.UpdateProductDto;
import com.mertyarimay.product_service.business.services.service.ProductService;
import com.mertyarimay.product_service.business.services.servicesRules.ProductServiceRules;
import com.mertyarimay.product_service.data.entity.ProductBrandEntity;
import com.mertyarimay.product_service.data.entity.ProductEntity;
import com.mertyarimay.product_service.data.repository.IProductBrandRepository;
import com.mertyarimay.product_service.data.repository.IProductRepository;
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
public class ProductServiceImpl implements ProductService {
    private final IProductRepository productRepository;
    private final ModelMapperService modelMapperService;
    private final IProductBrandRepository productBrandRepository;
    private final ProductServiceRules productServiceRules;
    @Override
    @Transactional  //bir database işlemi yapılıyorsa kullanılması gerekiyor
    public CreateProductDto create(CreateProductDto createProductDto) {
        if(createProductDto !=null){
            ProductBrandEntity productBrandEntity=productBrandRepository.findById(createProductDto.getProductBrandId()).orElse(null);
            if(productBrandEntity!=null){
                ProductEntity productEntity=modelMapperService.forRequest().map(createProductDto,ProductEntity.class);
                productEntity.setProductBrandEntity(productBrandEntity);
                productRepository.save(productEntity);
                CreateProductDto createProduct=modelMapperService.forRequest().map(productEntity,CreateProductDto.class);
                return createProduct;
            }
            else {
                throw new BusinessException("Girdiğiniz productBrandId Mevcut değil");
            }

        }
        return null;



    }

    @Override
    public List<GetAllProductDto >getAll() {
        List<ProductEntity>productEntities=productRepository.findAll();
        List<GetAllProductDto>getAllProductDtos=productEntities.stream().map(productEntity -> modelMapperService.forResponse()
                .map(productEntity, GetAllProductDto.class))
                .collect(Collectors.toList());
        return getAllProductDtos;

    }

    @Override
    public List<GetAllProductDto> getAllBrandId(Optional<Integer>productBrandId) {
        if(productBrandId.isPresent()){
            ProductBrandEntity productBrandEntity=productBrandRepository.findById(productBrandId.get()).orElse(null);
            if(productBrandEntity==null){
                throw new BusinessException("Giridiğiniz BrandId Mevcut değildir");
            }else{
                List<ProductEntity>productEntities=productRepository.findByProductBrandEntity_Id(productBrandId.get());
                List<GetAllProductDto>getAllProductDtos=productEntities.stream()
                        .map(productEntity -> modelMapperService.forResponse()
                                .map(productEntity, GetAllProductDto.class)).collect(Collectors.toList());
                return getAllProductDtos;

            }
        }
        return null;

    }

    @Override
    public GetByIdProductDto getById(int id) {
        ProductEntity productEntity=productRepository.findById(id).orElse(null);
        if(productEntity!=null){
            GetByIdProductDto getByIdProductDto=modelMapperService.forResponse().map(productEntity, GetByIdProductDto.class);
            return getByIdProductDto;
        }
        else {
            return null;
        }
    }

    @Override
    public UpdateProductDto update(UpdateProductDto updateProductDto, int id) {
       ProductEntity productEntity=productRepository.findById(id).orElse(null);
       if(productEntity!=null){
           productServiceRules.checkPrice(id,updateProductDto.getProductPrice());
           productEntity.setProductPrice(updateProductDto.getProductPrice());
           productRepository.save(productEntity);
           UpdateProductDto updateProduct=modelMapperService.forRequest().map(productEntity,UpdateProductDto.class);
           return updateProduct;
       }
       return null;
    }

    @Override
    public boolean delete(int id) {
        ProductEntity productEntity=productRepository.findById(id).orElse(null);
        if(productEntity!=null){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
