package com.mertyarimay.product_service.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)  //Otomatik olarak eklensin diye  TIMESTAMP bu hem zaman hem tarih olarak ekler
    private Date createdDate;
}
