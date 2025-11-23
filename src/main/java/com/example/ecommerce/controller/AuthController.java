package com.example.ecommerce.controller;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepo;

	 @PostMapping("/register")
	    public ResponseEntity<String> register(@RequestBody User user) {
	        if (userRepo.findByUsername(user.getUsername()) != null) {
	            return ResponseEntity.badRequest().body("Username already exists!");
	        }
	        user.setRole("USER");
	        userRepo.save(user);
	        return ResponseEntity.ok("User registered successfully!");
	    }

	
	@PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        User existing = userRepo.findByUsername(user.getUsername());
        Map<String, String> response = new HashMap<>();

        if (existing == null) {
            response.put("message", "User not found!");
            return ResponseEntity.status(400).body(response);
        }
        if (!existing.getPassword().equals(user.getPassword())) {
            response.put("message", "Invalid password!");
            return ResponseEntity.status(400).body(response);
        }

        response.put("message", "Login successful!");
        response.put("role", existing.getRole());
        return ResponseEntity.ok(response);
    }
}
