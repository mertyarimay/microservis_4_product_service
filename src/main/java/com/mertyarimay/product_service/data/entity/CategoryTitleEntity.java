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
@Table(name = "Product_Category_Title")
public class CategoryTitleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String categoryTitleName;

    @OneToMany(mappedBy = "categoryTitleEntity")
    private List<CategoryEntity>categoryEntities;
}
