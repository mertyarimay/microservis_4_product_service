package com.mertyarimay.product_service.business.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
    public static final Long serialVersionUID=1L;

    private int id;
    @NotBlank
    @NotNull
    @Size(min = 1)
    private String productName;
    @NotBlank
    @NotNull
    private double productPrice;
    private Date createdDate;

}
