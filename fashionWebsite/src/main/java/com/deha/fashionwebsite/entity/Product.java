package com.deha.fashionwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_type_id")
    @NotNull(message = "Select Product type!")
    private Integer productTypeId;

    @NotEmpty(message = "Name can't be empty!")
    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "madein")
    private String madein;

    @Column(name = "price")
    private float price;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
