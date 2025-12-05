package com.spring.projects.JPA;

import com.spring.projects.JPA.entities.ProductEntity;
import com.spring.projects.JPA.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaApplicationTests {

    @Autowired
    ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

    @Test
    void ProductRepositoryTest()
    {
        ProductEntity productEntity = ProductEntity.builder()
                .sku("Study Lamp")
                .title("Weird Wolf")
                .price(BigDecimal.valueOf(2334))
                .quantity(30)
                .build();

        ProductEntity productEntity1 = ProductEntity.builder()
                .sku("Mobile")
                .title("Samsung")
                .price(BigDecimal.valueOf(13000))
                .quantity(30)
                .build();

        ProductEntity savedProductEntity1=productRepository.save(productEntity1);
        ProductEntity savedProductEntity=productRepository.save(productEntity);
        System.out.println(savedProductEntity);
        System.out.println(savedProductEntity1);
    }

    @Test
    void getAllEntity(){
//        List<ProductEntity> productEntityList = productRepository.findAll();
//        System.out.println(productEntityList);

//          List<ProductEntity> productEntityList = productRepository.findByCreatedAtAfter(
//                  LocalDateTime.of(2025,1,1,0,0,0));

//        List<ProductEntity> productEntityList = productRepository.findByQuantityAndPrice(4,BigDecimal.valueOf(30));

        List<ProductEntity> productEntityList = productRepository.findByQuantityOrPrice(30,BigDecimal.valueOf(13000));
        System.out.println(productEntityList);

        List<ProductEntity> productEntityList1 = productRepository.findByQuantityLessThanAndPriceGreaterThan(50,BigDecimal.valueOf(30));
        System.out.println(productEntityList1);

        Optional<ProductEntity> productEntityList2 = productRepository.findByTitleAndPrice("Samsung",BigDecimal.valueOf(13000));
        productEntityList2.ifPresent(System.out::println);

    }

}
