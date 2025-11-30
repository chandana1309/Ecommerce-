package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	
	 @Autowired
	    private ProductRepository productRepo;

	    @Autowired
	    private UserRepository userRepo;

	    @Autowired
	    private OrderRepository orderRepo;

	   
	    
	    @PostMapping("/products")
	    public ResponseEntity<String> addProduct(@RequestBody Product product) {
	        productRepo.save(product);
	        return ResponseEntity.ok("Product added successfully!");
	    }

	  
	    @PutMapping("/products/{id}")
	    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
	        Product existing = productRepo.findById(id).orElse(null);
	        if (existing == null) {
	            return ResponseEntity.badRequest().body("Product not found!");
	        }
	        existing.setName(product.getName());
	        existing.setDescription(product.getDescription());
	        existing.setPrice(product.getPrice());
	        existing.setStock(product.getStock());

	        productRepo.save(existing);
	        return ResponseEntity.ok("Product updated!");
	    }

	    
	    @DeleteMapping("/products/{id}")
	    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
	        productRepo.deleteById(id);
	        return ResponseEntity.ok("Product deleted!");
	    }

	  
	    @GetMapping("/users")
	    public List<User> getAllUsers() {
	        return userRepo.findAll();
	    }

	   
	    @GetMapping("/orders")
	    public List<Order> getAllOrders() {
	        return orderRepo.findAll();
	    }
	}

