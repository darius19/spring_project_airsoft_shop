package com.airsoftShop.airsoftShop.controller;


import com.airsoftShop.airsoftShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping({"/products"})
    public String getProductForm(Model model){
      model.addAttribute("products", productService.getAll());
        return "products-page";
    }



}
