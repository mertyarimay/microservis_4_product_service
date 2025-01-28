package com.mertyarimay.product_service.business.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductDto {
    private String productName;
    private double productPrice;
    private String description;
    private String colour;
    private String brandName;
}
