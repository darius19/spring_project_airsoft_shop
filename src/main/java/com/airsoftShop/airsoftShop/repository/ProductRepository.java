package com.airsoftShop.airsoftShop.repository;

import com.airsoftShop.airsoftShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long > {
}
