package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping()
	public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
		return productService.getAllProducts(pageable);
	}

	@GetMapping("/{di}")
	public ResponseEntity<Product> getProductById(@PathVariable long di) {
		return productService.getProductById(di);
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	@PutMapping("/{di}")
	public ResponseEntity<Product> updateProduct(@PathVariable long di, @RequestBody Product product) {
		return productService.updateProduct(di, product);
	}

	@DeleteMapping("/{di}")
	public ResponseEntity<Void> deleteProduct(@PathVariable long di) {
		return productService.deleteProduct(di);
	}
}
