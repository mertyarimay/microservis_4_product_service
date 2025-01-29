package com.mertyarimay.product_service.business.dto.categoryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetByIdCategoryDto {
    private String categoryName;
    private List<String>productList;
}
