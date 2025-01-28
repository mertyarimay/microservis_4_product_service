package com.mertyarimay.product_service.business.dto.categoryTitleDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCategoryTitleDto {
    @NotNull
    @NotBlank
    private String categoryTitleName;
}
