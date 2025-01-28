package com.mertyarimay.product_service.business.dto.productDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto implements Serializable {
    public static final Long serialVersionUID=1L;

    private int id;
    @NotBlank
    @NotNull
    private String productName;
    @NotNull
    private double productPrice;
    @NotNull
    @Size(min = 1)
    private String description;
    @NotNull
    @Size(min = 1)
    private String colour;
    @NotNull
    private int productBrandId;
}
