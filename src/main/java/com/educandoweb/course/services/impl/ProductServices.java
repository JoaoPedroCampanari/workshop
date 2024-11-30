package com.educandoweb.course.services.impl;

import com.educandoweb.course.domain.dtos.ProductDto;
import com.educandoweb.course.domain.dtos.UsersDto;
import com.educandoweb.course.domain.entities.Category;
import com.educandoweb.course.domain.entities.Orders;
import com.educandoweb.course.domain.entities.Product;
import com.educandoweb.course.domain.entities.Users;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ProductServices {

    List<Product> findAll();

    Product findById(UUID id);

    Product save(ProductDto productDto);

    String deleteById(UUID id);

    Product update(UUID id, ProductDto productDto);

    Set<Category> findAllCategoryByProductId(UUID id);
}
