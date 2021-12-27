package com.deha.fashionwebsite.repository;

import com.deha.fashionwebsite.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductTypeRepository extends JpaRepository<ProductType, Long>{
    //    ProductType findByName(String name);
}
