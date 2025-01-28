package com.mertyarimay.product_service.business.services.service;

import com.mertyarimay.product_service.business.dto.productBrandDto.CreateProductBrandDto;
import com.mertyarimay.product_service.business.dto.productBrandDto.GetAllProductBrandDto;
import com.mertyarimay.product_service.business.dto.productBrandDto.GetByIdProductBrandDto;


import java.util.List;
import java.util.Optional;

public interface ProductBrandService {
    CreateProductBrandDto create(CreateProductBrandDto createProductBrandDto);
  List<GetAllProductBrandDto> getAll(Optional<Integer>categoryId);
  GetByIdProductBrandDto getById(int id);
  boolean delete(int id);
}
