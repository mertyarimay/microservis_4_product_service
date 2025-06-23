package com.mertyarimay.product_service.controller;

import com.mertyarimay.product_service.business.dto.categoryTitleDto.CreateCategoryTitleDto;
import com.mertyarimay.product_service.business.dto.categoryTitleDto.GetAllCategoryTitleDto;
import com.mertyarimay.product_service.business.dto.categoryTitleDto.GetByIdCategoryTitleDto;
import com.mertyarimay.product_service.business.services.service.CategoryTitleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/getAll")
    public List<GetAllCategoryTitleDto>getAll(){
        List<GetAllCategoryTitleDto> getAllCategoryTitleDtos =categoryTitleService.getAll();
        return getAllCategoryTitleDtos;
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Object>getById(@PathVariable("id")int id){
        GetByIdCategoryTitleDto getByIdCategoryTitleDto=categoryTitleService.getById(id);
        if(getByIdCategoryTitleDto!=null){
            return ResponseEntity.ok(getByIdCategoryTitleDto);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Girdiğini Id BULUNAMADI");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object>delete(@PathVariable("id")int id){
        boolean delete=categoryTitleService.delete(id);
        if(delete==true){
            return ResponseEntity.ok("Silme İşlemi Başarılı Bir şekilde gerçekleşti");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Silme İşlemi Başarısız");
        }
    }
}
