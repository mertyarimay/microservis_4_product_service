package com.mertyarimay.product_service.business.services.servicesRules;

import com.mertyarimay.product_service.data.entity.CategoryTitleEntity;
import com.mertyarimay.product_service.data.repository.ICategoryRepository;
import com.mertyarimay.product_service.data.repository.ICategoryTitleRepository;
import com.mertyarimay.product_service.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryServiceRules {
    private final ICategoryTitleRepository categoryTitleRepository;

    public void checkCategoryTitleId(int categoryTitleId){
        CategoryTitleEntity categoryTitleEntity=categoryTitleRepository.findById(categoryTitleId).orElse(null);
        if(categoryTitleEntity==null){
            throw new BusinessException("Giridiğiniz CategoryTitleId Mevcut Değildir");
        }

    }


}
