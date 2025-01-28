package com.mertyarimay.product_service.business.services.servicesRules;

import com.mertyarimay.product_service.data.entity.ProductEntity;
import com.mertyarimay.product_service.data.repository.IProductRepository;
import com.mertyarimay.product_service.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceRules {
    private final IProductRepository productRepository;


    public void checkPrice(int id,double productPrice){
        ProductEntity productEntity=productRepository.checkPrice(id,productPrice);
        if(productEntity!=null){
            throw new BusinessException("Güncellemek istediğiniz Fiyat Bir önceki fiyat ile aynı olamaz");
        }
    }
}
