package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	 @GetMapping
	    public Page<Category> getProductList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
	        Pageable paging = PageRequest.of(page, size);
	        return categoryService.getAllCategories(paging);
	    }

	@GetMapping("/{di}")
	public ResponseEntity<Category> getCategoryById(@PathVariable long di) {
		return categoryService.getCategoryById(di);
	}

	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}

	@PutMapping("/{di}")
	public ResponseEntity<Category> updateCategory(@PathVariable long di, @RequestBody Category category) {
		return categoryService.updateCategory(di, category);
	}

	@DeleteMapping("/{di}")
	public ResponseEntity<Void> deleteCategory(@PathVariable long di) {
		return new CategoryService().deleteCategory(di);
	}
}
