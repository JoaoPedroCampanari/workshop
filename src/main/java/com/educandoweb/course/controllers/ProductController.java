package com.educandoweb.course.controllers;

import com.educandoweb.course.domain.dtos.ProductDto;
import com.educandoweb.course.domain.entities.Category;
import com.educandoweb.course.domain.entities.Product;
import com.educandoweb.course.services.impl.ProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(productServices.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productServices.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productServices.save(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(name = "id") UUID id, @RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.OK).body(productServices.update(id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productServices.deleteById(id));
    }

    @GetMapping("/Allcategories/{id}")
    public ResponseEntity<Set<Category>> findAllCategoryByProductId(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productServices.findAllCategoryByProductId(id));
    }

}
