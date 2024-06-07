package com.airsoftShop.airsoftShop.service;


import com.airsoftShop.airsoftShop.model.Product;
import com.airsoftShop.airsoftShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void saveProduct(Product product){
        productRepository.save(product);

    }
    
    
    public List<Product> getAll(){

        return productRepository.findAll();

    }
}
