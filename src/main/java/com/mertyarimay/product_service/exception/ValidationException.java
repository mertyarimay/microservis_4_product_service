package com.mertyarimay.product_service.exception;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationException extends ProblemDetails {
    private Map<String,String>validationErrors;
}
