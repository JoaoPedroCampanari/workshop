package com.educandoweb.course.services;

import com.educandoweb.course.domain.dtos.ProductDto;
import com.educandoweb.course.domain.entities.Category;
import com.educandoweb.course.domain.entities.Product;
import com.educandoweb.course.exception.products.ProductNameAlreadyExistException;
import com.educandoweb.course.exception.products.ProductNotFoundException;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.impl.ProductServices;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductServicesImpl implements ProductServices {

    private final ProductRepository productRepository;

    public ProductServicesImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public Product save(ProductDto productDto) {
        if (productRepository.existsByName(productDto.getName())){
            throw new ProductNameAlreadyExistException("Product name already exist!");
        }

        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

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

    @Override
    public Product update(UUID id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        BeanUtils.copyProperties(productDto, product);
        return productRepository.save(product);
    }

    @Override
    public Set<Category> findAllCategoryByProductId(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return product.getCategories();
    }
}
