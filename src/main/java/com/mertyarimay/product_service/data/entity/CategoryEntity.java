package com.mertyarimay.product_service.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String categoryName;
    @ManyToOne
    @JoinColumn(name = "category_title_id")
    private CategoryTitleEntity categoryTitleEntity;

    @OneToMany(mappedBy = "categoryEntity")
    private List<ProductBrandEntity>productBrandEntities;





}
