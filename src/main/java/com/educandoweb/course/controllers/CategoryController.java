package com.educandoweb.course.controllers;

import com.educandoweb.course.domain.dtos.CategoryDto;
import com.educandoweb.course.domain.entities.Category;
import com.educandoweb.course.domain.entities.Product;
import com.educandoweb.course.services.impl.CategoryServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServices categoryServices;

    public CategoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @PostMapping
    public ResponseEntity<Category> save (@RequestBody CategoryDto categoryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryServices.save(categoryDto));
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryServices.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryServices.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryServices.deleteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable(name = "id") UUID id, @RequestBody CategoryDto categoryDto){
        return ResponseEntity.status(HttpStatus.OK).body(categoryServices.update(id, categoryDto));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Set<Product>> findAllProductsByCategoryId (@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryServices.findAllProductsByCategoryId(id));
    }


}
