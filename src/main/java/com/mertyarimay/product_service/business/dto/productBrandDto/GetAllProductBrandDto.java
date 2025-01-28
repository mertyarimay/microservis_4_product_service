package com.mertyarimay.product_service.business.dto.productBrandDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllProductBrandDto {
    private String categoryName;
    private String brandName;
    private List<String> productList;
}
