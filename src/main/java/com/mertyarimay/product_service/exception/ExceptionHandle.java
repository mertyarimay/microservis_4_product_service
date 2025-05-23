package com.mertyarimay.product_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice //hataları tek bir sınıfta yakalamak için kullanılır
public class ExceptionHandle {
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)  //exception fırlatıldığında altındaki metot çalışssın diye kullanılır
    public ProblemDetails exceptionHandle(BusinessException businessException){
        ProblemDetails problemDetails=new ProblemDetails();
        problemDetails.setMessage(businessException.getMessage());
        return problemDetails;
    }
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetails validationProblems(MethodArgumentNotValidException methodArgumentNotValidException){
        ValidationException validationException=new ValidationException();
        validationException.setMessage("Validation Exception");
        validationException.setValidationErrors(new HashMap<>());
        for (FieldError fieldError:methodArgumentNotValidException.getBindingResult().getFieldErrors()){
            validationException.getValidationErrors().put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return validationException;
    }
}
