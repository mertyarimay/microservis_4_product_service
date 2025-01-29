package com.mertyarimay.product_service.data.repository;

import com.mertyarimay.product_service.data.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    List<CategoryEntity> findByCategoryTitleEntity_Id(Integer categoryTitleId);
}
