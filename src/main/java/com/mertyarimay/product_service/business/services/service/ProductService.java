package com.mertyarimay.product_service.business.services.service;

import com.mertyarimay.product_service.business.dto.productDto.CreateProductDto;
import com.mertyarimay.product_service.business.dto.productDto.GetAllProductDto;
import com.mertyarimay.product_service.business.dto.productDto.GetByIdProductDto;
import com.mertyarimay.product_service.business.dto.productDto.UpdateProductDto;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    CreateProductDto create(CreateProductDto createProductDto);
   List<GetAllProductDto> getAll();
   List<GetAllProductDto>getAllBrandId(Optional<Integer> productBrandId);
   GetByIdProductDto getById(int id);
   UpdateProductDto update(UpdateProductDto updateProductDto,int id);
   boolean delete(int id);


}
