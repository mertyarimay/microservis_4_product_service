package com.mertyarimay.product_service.business.dto.categoryTitleDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllCategoryTitleDto {
    private String CategoryTitleName;
    private int id;
}
