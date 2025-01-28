package com.mertyarimay.product_service.business.services.servicesRules;

import com.mertyarimay.product_service.data.entity.CategoryEntity;
import com.mertyarimay.product_service.data.repository.ICategoryRepository;
import com.mertyarimay.product_service.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductBrandServicesRules {
    private final ICategoryRepository categoryRepository;


    public void checkCategoryId(int categoryId){
        CategoryEntity categoryEntity=categoryRepository.findById(categoryId).orElse(null);
        if(categoryEntity==null){
            throw new BusinessException("Girdiğiniz categoryId Mevcut değildir");


        }
    }
}
