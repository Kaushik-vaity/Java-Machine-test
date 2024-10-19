package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
		Page<Product> products = productRepository.findAll(pageable);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	public ResponseEntity<Product> getProductById(long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);

		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();

			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Product not found with id: " + id);
		}
	}

	public ResponseEntity<Product> createProduct(Product product) {

		Product newProduct = productRepository.save(product);

		return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
	}

	public ResponseEntity<Product> updateProduct(long id, Product productDetails) {
		Optional<Product> optionalProduct = productRepository.findById(id);

		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();

			product.setProductName(productDetails.getProductName());
			product.setProductPrice(productDetails.getProductPrice());
			product.setCategory(productDetails.getCategory());

			Product newProduct = productRepository.save(product);

			return new ResponseEntity<>(newProduct, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Product not found with id: " + id);
		}

	}

	public ResponseEntity<Void> deleteProduct(long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			productRepository.delete(optionalProduct.get());

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new ResourceNotFoundException("Product not found with id: " + id);
		}
	}
}
