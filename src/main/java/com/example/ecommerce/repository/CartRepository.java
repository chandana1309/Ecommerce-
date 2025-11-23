package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.User;

public interface CartRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByUser(User user);
	}
  


