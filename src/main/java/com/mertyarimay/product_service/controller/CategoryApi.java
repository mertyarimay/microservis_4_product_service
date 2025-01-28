package com.mertyarimay.product_service.controller;

import com.mertyarimay.product_service.business.dto.categoryDto.CreateCategoryDto;
import com.mertyarimay.product_service.business.services.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/category/api")
public class CategoryApi {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Object>create(@RequestBody @Valid CreateCategoryDto createCategoryDto){
        CreateCategoryDto createCategory=categoryService.create(createCategoryDto);
        if(createCategory!=null){
            return ResponseEntity.ok(createCategory);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kayıt İşlemi Başarısız");
    }
}
