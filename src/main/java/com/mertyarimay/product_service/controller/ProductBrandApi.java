package com.mertyarimay.product_service.controller;

import com.mertyarimay.product_service.business.dto.productBrandDto.CreateProductBrandDto;
import com.mertyarimay.product_service.business.dto.productBrandDto.GetAllProductBrandDto;
import com.mertyarimay.product_service.business.dto.productBrandDto.GetByIdProductBrandDto;
import com.mertyarimay.product_service.business.services.service.ProductBrandService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/product/brand/api")
public class ProductBrandApi {
    private final ProductBrandService productBrandService;


    @PostMapping("/create")
    public ResponseEntity<Object>create(@RequestBody @Valid CreateProductBrandDto createProductBrandDto){
        CreateProductBrandDto createProductBrand=productBrandService.create(createProductBrandDto);
        if(createProductBrand!=null){
            return ResponseEntity.ok("Kayıt İşleminiz Başarılı Bir Şekilde Gerçekleşti");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kayıt İşlemi Başrısız Oldu");
    }

    @GetMapping()
    public ResponseEntity<Object>getAll(@RequestParam Optional<Integer>categoryId){
        List<GetAllProductBrandDto>getAllProductBrandDtos=productBrandService.getAll(categoryId);
        if(getAllProductBrandDtos!=null){
            return ResponseEntity.ok(getAllProductBrandDtos);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen listesini görmek istediğiniz category ıd yi giriniz");
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object>getById(@PathVariable("id") int id){
        GetByIdProductBrandDto getByIdProductBrandDto=productBrandService.getById(id);
        if(getByIdProductBrandDto!=null){
            return ResponseEntity.ok(getByIdProductBrandDto);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Girdiğiniz Id Bulunamadı");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object>delete(@PathVariable("id") int id){
        boolean delete=productBrandService.delete(id);
        if(delete==true){
            return ResponseEntity.ok("Silme İşlemi Başarılı Bir Şekilde Gerçekleşti");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Silme İşlemi Başarısız");
        }
    }
}
