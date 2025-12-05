package com.spring.projects.JPA.repositories;

import com.spring.projects.JPA.dto.ProductInfoConcreat;
import com.spring.projects.JPA.dto.ProductProjection;
import com.spring.projects.JPA.entities.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByCreatedAtAfter(LocalDateTime createdAt);

    List<ProductEntity> findByQuantityAndPrice(Integer i, BigDecimal bigDecimal);

    List<ProductEntity> findByQuantityOrPrice(Integer i, BigDecimal bigDecimal);

    List<ProductEntity> findByQuantityLessThanAndPriceGreaterThan(Integer i, BigDecimal bigDecimal);

    @Query("select e from ProductEntity e where e.title=:title and e.price=:price")
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

    List<ProductEntity> findByOrderByPrice();

    List<ProductEntity> findBy(Sort sort);

    @Query(value = "SELECT id AS id, product_title AS title, price AS price FROM product_table", nativeQuery = true)
    List<ProductProjection> findAllProductProjection();

    @Query(value = "SELECT new com.spring.projects.JPA.dto.ProductInfoConcreat(p.id, p.title) FROM ProductEntity p")
    List<ProductInfoConcreat> findAllProductProjectionConcreat();

}
