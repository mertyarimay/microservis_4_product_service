package com.mertyarimay.product_service.data.repository;

import com.mertyarimay.product_service.data.entity.ProductBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductBrandRepository extends JpaRepository<ProductBrandEntity,Integer> {
    List<ProductBrandEntity>findByCategoryEntity_Id(Integer categoryId);
}
