package com.airsoftShop.airsoftShop.service;


import com.airsoftShop.airsoftShop.model.Product;
import com.airsoftShop.airsoftShop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest{

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    public void saveProduct_whenCorrectProduct_thenSaveProduct_test(){
//        given
        Product givenProduct = new Product();
        givenProduct.setId(1L);
        givenProduct.setName("mere");
        givenProduct.setDescription("acesta este un mar");
        givenProduct.setPrice("200$");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setName("mere");
        expectedProduct.setDescription("acesta este un mar");
        expectedProduct.setPrice("200$");

        when(productRepository.save(any())).thenReturn(expectedProduct);


//        when
        productService.saveProduct(givenProduct);

//        then
        assertEquals(givenProduct.getId(),expectedProduct.getId());
        verify(productRepository,times(1)).save(any());

    }
}
