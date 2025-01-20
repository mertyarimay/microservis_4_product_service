package com.mertyarimay.product_service.business.services.impl;

import com.mertyarimay.product_service.bean.ModelMapperConfig;
import com.mertyarimay.product_service.business.dto.ProductDto;
import com.mertyarimay.product_service.business.services.ProductService;
import com.mertyarimay.product_service.data.entity.ProductEntity;
import com.mertyarimay.product_service.data.repository.IProductRepository;
import com.mertyarimay.product_service.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final IProductRepository productRepository;
    private final ModelMapperConfig modelMapper;



    @Override
    public ProductEntity dtoToEntity(ProductDto productDto) {
        return modelMapper.modelMapper().map(productDto,ProductEntity.class);
    }

    @Override
    public ProductDto entityToDto(ProductEntity productEntity) {
        return modelMapper.modelMapper().map(productEntity,ProductDto.class);
    }

    @Override
    @Transactional  //bir database işlemi yapılıyorsa kullanılması gerekiyor
    public ProductDto create(ProductDto productDto) {
        if(productDto!=null){
            ProductEntity productEntity=dtoToEntity(productDto);
            productRepository.save(productEntity);
            productDto.setId(productEntity.getId());
            productDto.setCreatedDate(productEntity.getCreatedDate());

        }
        else {
            throw new BusinessException("Product dto NULL");
        }
        return productDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductEntity>productEntities=productRepository.findAll();
        List<ProductDto>productDtos=new ArrayList<>();
        for(ProductEntity temp:productEntities){
            ProductDto productDto=entityToDto(temp);
            productDtos.add(productDto);

        }
        return productDtos;

    }

    @Override
    public ProductDto getById(int id) {
        ProductEntity productEntity=productRepository.findById(id).orElse(null);
        if(productEntity!=null){
            ProductDto productDto=entityToDto(productEntity);
            return productDto;
        }
        return null;
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDto, int id) {
        ProductEntity productEntity=productRepository.findById(id).orElse(null);
        if(productEntity!=null){
            productEntity.setProductName(productDto.getProductName());
            productEntity.setProductPrice(productDto.getProductPrice());
            productRepository.save(productEntity);
            ProductDto productModel=entityToDto(productEntity);
            return productModel;
        }
        return null;

    }

    @Override
    @Transactional
    public Boolean delete(int id) {
        Optional<ProductEntity> productEntity=productRepository.findById(id);
        if(productEntity.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
