package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
	  @Autowired
	    private OrderRepository orderRepo;
	    @Autowired
	    private UserRepository userRepo;
	    @Autowired
	    private CartRepository cartRepo;

	    @PostMapping("/place/{username}")
	    public ResponseEntity<String> placeOrder(@PathVariable String username) {
	        User user = userRepo.findByUsername(username);
	        List<CartItem> cartItems = cartRepo.findByUser(user);

	        double total = cartItems.stream()
	                .mapToDouble(c -> c.getProduct().getPrice() * c.getQuantity())
	                .sum();

	        Order order = new Order(user, total);
	        orderRepo.save(order);

	        cartRepo.deleteAll(cartItems);
	        return ResponseEntity.ok("Order placed successfully!");
	    }

	    @GetMapping("/{username}")
	    public List<Order> getUserOrders(@PathVariable String username) {
	        User user = userRepo.findByUsername(username);
	        return orderRepo.findByUser(user);
	    }

}
