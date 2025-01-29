package com.mertyarimay.product_service.business.dto.categoryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllCategoryTitleIdDto {
    private String categoryTitleName;
    private String categoryName;
    private List<String>productBrandList;
    private List<String>productList;
}
