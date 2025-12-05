package com.spring.projects.JPA.controller;

import com.spring.projects.JPA.entities.ProductEntity;
import com.spring.projects.JPA.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/get")
    public List<ProductEntity> getAllProduct(@RequestParam(defaultValue = "id") String sortBy)
    {
        return productRepository.findBy(Sort.by(Sort.Direction.DESC,sortBy));
    }

    @GetMapping
    public Page<ProductEntity> getAllProduct(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        return productRepository.findAll(pageable);
    }



}
