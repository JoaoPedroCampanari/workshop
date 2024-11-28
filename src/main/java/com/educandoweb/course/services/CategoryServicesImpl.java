package com.educandoweb.course.services;

import com.educandoweb.course.domain.dtos.CategoryDto;
import com.educandoweb.course.domain.entities.Category;
import com.educandoweb.course.exception.category.CategoryNameAlreadyExistException;
import com.educandoweb.course.exception.category.CategoryNotFoundException;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.services.impl.CategoryServices;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServicesImpl implements CategoryServices {

    private final CategoryRepository categoryRepository;

    public CategoryServicesImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(CategoryDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.getName())){
            throw new CategoryNameAlreadyExistException("Category already exist by name: " + categoryDto.getName());
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(UUID id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        BeanUtils.copyProperties(categoryDto, category);
        return categoryRepository.save(category);
    }

    @Override
    public String deleteById(UUID id) {
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return ("Category deleted successfully. ID: " + id);
        }
        else {
            throw new CategoryNotFoundException("Category not found with id: " + id);
        }
    }
}
