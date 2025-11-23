package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cart")
public class CartController {

	 @Autowired
	    private CartRepository cartRepo;
	    @Autowired
	    private UserRepository userRepo;
	    @Autowired
	    private ProductRepository productRepo;

	    @PostMapping("/add/{username}/{productId}")
	    public ResponseEntity<String> addToCart(@PathVariable String username, @PathVariable Long productId) {
	        User user = userRepo.findByUsername(username);
	        Product product = productRepo.findById(productId).orElse(null);
	        if (user == null || product == null) return ResponseEntity.badRequest().body("Invalid data!");

	        CartItem item = new CartItem(user, product, 1);
	        cartRepo.save(item);
	        return ResponseEntity.ok("Added to cart!");
	    }

	    @GetMapping("/{username}")
	    public List<CartItem> viewCart(@PathVariable String username) {
	        User user = userRepo.findByUsername(username);
	        return cartRepo.findByUser(user);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> removeFromCart(@PathVariable Long id) {
	        cartRepo.deleteById(id);
	        return ResponseEntity.ok("Item removed!");
	    }
}
