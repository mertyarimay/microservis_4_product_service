package com.mertyarimay.product_service.business.dto.productBrandDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductBrandDto {
    private int id;

    @NotBlank
    @NotNull
    private String brandName;
    @NotNull
    private int categoryId;
}
