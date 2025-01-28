package com.mertyarimay.product_service.business.dto.productDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProductDto {
    @NotNull
    private double productPrice;
}
