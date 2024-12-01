package com.educandoweb.course.services;

import com.educandoweb.course.domain.dtos.ProductDto;
import com.educandoweb.course.domain.entities.Category;
import com.educandoweb.course.domain.entities.Product;
import com.educandoweb.course.exception.category.CategoryNotFoundException;
import com.educandoweb.course.exception.products.ProductNameAlreadyExistException;
import com.educandoweb.course.exception.products.ProductNotFoundException;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.impl.ProductServices;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductServicesImpl implements ProductServices {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServicesImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    @Transactional
    public Product save(ProductDto productDto) {
        if (productRepository.existsByName(productDto.getName())){
            throw new ProductNameAlreadyExistException("Product name already exist!");
        }

        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

        for (UUID id : productDto.getCategoriesId()){
            Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
            product.addCategoryList(category);
        }

        return productRepository.save(product);
    }

    @Override
    public String deleteById(UUID id) {
        if (!productRepository.existsById(id)){
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        return "Product successfully deleted";
    }

    @Transactional
    @Override
    public Product update(UUID id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        BeanUtils.copyProperties(productDto, product);

        product.getCategories().clear();
        for(UUID categoryId : productDto.getCategoriesId()){
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));
            product.addCategoryList(category);
        }

        return productRepository.save(product);
    }

    @Override
    public Set<Category> findAllCategoryByProductId(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return product.getCategories();
    }
}
