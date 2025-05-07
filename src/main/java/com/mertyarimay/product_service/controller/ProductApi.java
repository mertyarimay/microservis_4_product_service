package com.mertyarimay.product_service.controller;

import com.mertyarimay.product_service.business.dto.productDto.CreateProductDto;
import com.mertyarimay.product_service.business.dto.productDto.GetAllProductDto;
import com.mertyarimay.product_service.business.dto.productDto.GetByIdProductDto;
import com.mertyarimay.product_service.business.dto.productDto.UpdateProductDto;
import com.mertyarimay.product_service.business.services.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/product/api")
@AllArgsConstructor

public class ProductApi {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid CreateProductDto createProductDto){
        CreateProductDto createProductModel=productService.create(createProductDto);
        if(createProductModel!=null){
            return    ResponseEntity.ok(createProductModel);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ürün Kayıt İşleminiz Başarısız Oldu");

    }

    @GetMapping("/getAll")
    public List<GetAllProductDto>getAll(){
        List<GetAllProductDto>getAllProductDtos=productService.getAll();
        return getAllProductDtos;
    }
    @GetMapping
    public ResponseEntity<Object>getAllBrandId(@RequestParam Optional<Integer>productBrandId){
        List<GetAllProductDto>getAllProductDtos=productService.getAllBrandId(productBrandId);
        if(getAllProductDtos!=null){
            return ResponseEntity.ok(getAllProductDtos);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen listesini almak istediğiniz brand ıdyi giriniz");
        }


    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Object>getById(@PathVariable("id") int id){
        GetByIdProductDto getByIdProductDto=productService.getById(id);
        if(getByIdProductDto!=null){
            return ResponseEntity.ok(getByIdProductDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("İstediğiniz Ürün Bulunamadı");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object>update(@RequestBody UpdateProductDto updateProductDto,@PathVariable("id") int id){
        UpdateProductDto updateProduct=productService.update(updateProductDto,id);
        if(updateProduct!=null){
            return ResponseEntity.ok("Güncelleme İşleminiz Başarılı bir şekilde tanımlandı");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Güncellemek İstediğiniz Id Bulunamadı");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object>delete(@PathVariable("id")int id){
        boolean delete=productService.delete(id);
        if(delete==true){
            return ResponseEntity.ok("Silme İşleminiz Başarılı Bir Şekilde Gerçekleşti");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Silmek İstediğiniz Id bulunamadığı için silme işlemi Başarısız");
        }
    }





}
