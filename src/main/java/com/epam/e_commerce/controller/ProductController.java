package com.epam.e_commerce.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.e_commerce.dto.ProductRequest;
import com.epam.e_commerce.entity.Product;
import com.epam.e_commerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products") 
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
	
	private final ProductService service;
	
	@GetMapping
	ResponseEntity<List<Product>> getAllProducts(){
		return ResponseEntity.ok(service.allProducts());
	}
	@GetMapping("/{id}")
	ResponseEntity<Product> byId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(service.getById(id));
	}
	@PostMapping
	ResponseEntity<Product> saveProduct(@RequestBody ProductRequest req) {
		return ResponseEntity.created(null).body(service.saveProduct(req));
	}
	@GetMapping("/category/{category}")
	ResponseEntity<Set<Product>> productsByCategory(@PathVariable("category") String category){
		return ResponseEntity.ok(service.getProdctsByCategory(category));
	}
	@GetMapping("/{category}/{id}")
	ResponseEntity<Product> productsByCategoryAndId(@PathVariable("category") String category,@PathVariable("id") Integer id){
		return ResponseEntity.ok(service.getByCategoryAndId(category, id));
	}
	@DeleteMapping("/{id}")
	ResponseEntity<Product> deleteById(@PathVariable("id")Integer id) {
		return ResponseEntity.ok(service.deleteById(id));
	}
	
	@GetMapping("/categories")
	ResponseEntity<Set<String>> allCategories(){
		return ResponseEntity.ok(service.categories());
	}
	@PutMapping("/{id}")
	ResponseEntity<Product> updateProduct(@PathVariable("id")Integer id,@RequestBody ProductRequest req) {
		return ResponseEntity.ok(service.updateProduct(id, req));
	}
}













