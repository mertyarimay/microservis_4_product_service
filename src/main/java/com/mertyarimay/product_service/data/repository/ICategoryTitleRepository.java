package com.mertyarimay.product_service.data.repository;

import com.mertyarimay.product_service.data.entity.CategoryTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryTitleRepository extends JpaRepository<CategoryTitleEntity,Integer> {
}
