package com.mertyarimay.product_service.controller;

import com.mertyarimay.product_service.business.dto.ProductDto;
import com.mertyarimay.product_service.business.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/product/api/v1")
@AllArgsConstructor

public class ProductApi {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid ProductDto productDto){
        ProductDto productModel=productService.create(productDto);
        if(productModel!=null){
            return    ResponseEntity.ok(productModel);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kayıt başarısız oldu");

    }
    @GetMapping("/getAll")
    public ResponseEntity<Object>getAll(){
        List<ProductDto> productDtos=productService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object>getById(@PathVariable("id")int id){
        ProductDto productDto=productService.getById(id);
        if(productDto!=null){
            return ResponseEntity.ok(productDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Girdiğiniz Id ye ait kayıt Bulunamadı");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object>update(@RequestBody @Valid ProductDto productDto,@PathVariable("id") int id){
        ProductDto productModel=productService.update(productDto,id);
        if(productModel!=null){
            return  ResponseEntity.ok("Güncelleme İşlemi Başarılı");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Güncellemek istediğiniz Id ye Ait Kayıt Bulunamadı");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object>delete(@PathVariable("id") int id){
        Boolean delete=productService.delete(id);
        if(delete==true){
            return ResponseEntity.ok("Kayıt Silme İşleminiz Başarılı bir Şekilde Gerçekleşti");

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Silmek İstediğiniz Kayıt Bulunamadı");
    }


}
