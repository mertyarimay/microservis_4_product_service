package com.mertyarimay.product_service.data.repository;

import com.mertyarimay.product_service.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity,Integer> {
    List<ProductEntity> findByProductBrandEntity_Id(Integer productBrandId);
    @Query("Select p from ProductEntity p where p.id=:id and p.productPrice=:productPrice")
    ProductEntity checkPrice(int id,double productPrice);
}
