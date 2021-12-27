package com.deha.fashionwebsite.repository;

import com.deha.fashionwebsite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p " +
            "where 1=1" +
            "and ( upper(p.name) like concat('%', upper(?1), '%') " +
            "       or upper(p.brand) like concat('%', upper(?1), '%') " +
            "       or upper(p.madein) like concat('%', upper(?1), '%')" +
            ")" )
    List<Product> searchProduct(String criteria);
}