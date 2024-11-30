package com.educandoweb.course.services.impl;

import com.educandoweb.course.domain.dtos.CategoryDto;
import com.educandoweb.course.domain.entities.Category;
import com.educandoweb.course.domain.entities.Product;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CategoryServices {

    Category findById(UUID id);

    List<Category> findAll();

    Category save(CategoryDto categoryDto);

    Category update(UUID id, CategoryDto categoryDto);

    String deleteById(UUID id);

    Set<Product> findAllProductsByCategoryId(UUID id);
}
