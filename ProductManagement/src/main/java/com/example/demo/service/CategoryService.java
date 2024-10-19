package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

	public ResponseEntity<Category> getCategoryById(long id) {
		Optional<Category> category = categoryRepository.findById(id);

		if (category.isPresent()) {
			return new ResponseEntity<>(category.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Category not found with id: " + id);
		}
	}

	public ResponseEntity<Category> createCategory(Category category) {
		Category newCategory = categoryRepository.save(category);
		return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
	}

	public ResponseEntity<Category> updateCategory(long id, Category categoryDetails) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);

		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			category.setCategoryName(categoryDetails.getCategoryName());
			Category updateCategory = categoryRepository.save(category);
			return new ResponseEntity<>(updateCategory, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Category not found with id: " + id);
		}

	}

	public ResponseEntity<Void> deleteCategory(long id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);

		if (optionalCategory.isPresent()) {
			categoryRepository.delete(optionalCategory.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new ResourceNotFoundException("Category not found with id: " + id);
		}
	}
}
