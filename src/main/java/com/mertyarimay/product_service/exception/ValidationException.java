package com.mertyarimay.product_service.exception;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidationException extends ProblemDetails {
    private Map<String,String>validationErrors;
}
