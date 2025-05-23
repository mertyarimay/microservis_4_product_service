package com.mertyarimay.product_service.business.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetByIdProductDto {
    private int id;
    private String productName;
    private double productPrice;
    private String description;
    private String colour;
    private String brandName;
    private int stockQuantity;
}
