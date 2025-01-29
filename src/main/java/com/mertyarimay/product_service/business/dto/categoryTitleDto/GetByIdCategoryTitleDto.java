package com.mertyarimay.product_service.business.dto.categoryTitleDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetByIdCategoryTitleDto {
    private String categoryTitleName;
    private List<String>categoryList;
    private List<String>productBrandList;
    private  List<String>productList;

}
