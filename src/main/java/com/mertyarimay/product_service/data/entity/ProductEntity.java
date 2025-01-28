package com.mertyarimay.product_service.data.entity;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity implements Serializable {
    public static final Long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="product_name")
    private String productName;

    @Column(name ="product_price")
    private double productPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "colour")
    private String colour ;

    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_brand_id")
    private ProductBrandEntity productBrandEntity;


    @PrePersist
    public void prePersist(){
        this.publishedAt=LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        this.updatedAt=LocalDateTime.now();
    }
}