package com.mertyarimay.product_service.controller;

import com.mertyarimay.product_service.business.dto.categoryDto.CreateCategoryDto;
import com.mertyarimay.product_service.business.dto.categoryDto.GetAllCategoryDto;
import com.mertyarimay.product_service.business.dto.categoryDto.GetAllCategoryTitleIdDto;
import com.mertyarimay.product_service.business.dto.categoryDto.GetByIdCategoryDto;
import com.mertyarimay.product_service.business.services.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping()
    public ResponseEntity<Object>getAll(@RequestParam Optional<Integer>categoryTitleId){
        List<GetAllCategoryTitleIdDto> getAllCategoryTitleIdDtos =categoryService.getAll(categoryTitleId);
        if(getAllCategoryTitleIdDtos !=null){
            return ResponseEntity.ok(getAllCategoryTitleIdDtos);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lütfen categoryTitleId giriniz");
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object>getById(@PathVariable("id") int id){
        GetByIdCategoryDto getByIdCategoryDto=categoryService.getById(id);
        if(getByIdCategoryDto!=null){
            return ResponseEntity.ok(getByIdCategoryDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Girdiğiniz CategoryId mevcut değildir");
        }
    }

    @GetMapping("/getAll")
    public List<GetAllCategoryDto>getAllCategory(){
        List<GetAllCategoryDto>getAllCategoryDtos=categoryService.getAllCategory();
        return getAllCategoryDtos;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object>delete(@PathVariable("id") int id){
        boolean delete=categoryService.delete(id);
        if(delete==true){
            return ResponseEntity.ok("Silme İşlemi Başarılı");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Silme İşlemi Başarısız");
        }
    }
}
