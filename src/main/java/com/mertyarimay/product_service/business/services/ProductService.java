package com.mertyarimay.product_service.business.services;

import com.mertyarimay.product_service.business.dto.ProductDto;
import com.mertyarimay.product_service.data.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    //dto convert entity
    ProductEntity dtoToEntity(ProductDto productDto);
    //Entity convert dto
    ProductDto entityToDto(ProductEntity productEntity);


    ProductDto create(ProductDto productDto);
    List<ProductDto> getAllProducts();
    ProductDto getById(int id);
    ProductDto update(ProductDto productDto,int id);
    Boolean delete(int id);
}
