package com.mertyarimay.product_service.business.dto.categoryDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCategoryDto {
    private int id;
    @NotNull
    @NotBlank
    private String categoryName;
    @NotNull
    private int categoryTitleId;


}
