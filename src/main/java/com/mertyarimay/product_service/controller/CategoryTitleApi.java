package com.mertyarimay.product_service.controller;

import com.mertyarimay.product_service.business.dto.categoryTitleDto.CreateCategoryTitleDto;
import com.mertyarimay.product_service.business.services.service.CategoryTitleService;
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
@RequestMapping("/category/title/api")
public class CategoryTitleApi {
    private final CategoryTitleService categoryTitleService;


    @PostMapping("/create")
    public ResponseEntity<Object>create(@RequestBody @Valid CreateCategoryTitleDto createCategoryTitleDto){
        CreateCategoryTitleDto createCategoryTitle=categoryTitleService.create(createCategoryTitleDto);
        if(createCategoryTitle!=null){
            return ResponseEntity.ok("Kayıt Başarılı Bir şekilde oluşturuldu.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("kayıt başarısız");
    }
}
