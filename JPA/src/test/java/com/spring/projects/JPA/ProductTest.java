package com.spring.projects.JPA;

import com.spring.projects.JPA.dto.ProductInfoConcreat;
import com.spring.projects.JPA.dto.ProductProjection;
import com.spring.projects.JPA.entities.ProductEntity;
import com.spring.projects.JPA.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testProduct()
    {

        List<ProductProjection> productProjections = productRepository.findAllProductProjection();
        //productProjections.forEach(p -> System.out.println(p.getId()+"-"+p.getTitle() + " - " + p.getPrice()));

        List<ProductInfoConcreat> productProjections1 = productRepository.findAllProductProjectionConcreat();
        productProjections1.forEach(p -> System.out.println(p.getId()+"-"+p.getTitle() ));

    }

}
