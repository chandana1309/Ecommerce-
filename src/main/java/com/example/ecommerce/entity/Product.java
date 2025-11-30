package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	public Product() {}


	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String description;
	    private double price;
	    private int stock;
	    private String imageUrl;
	    private String category;
	    private double rating = 0.0;
	    private int ratingCount = 0;


	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		public String getImageUrl() {
		    return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
		    this.imageUrl = imageUrl;
		}
		

		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		
		public double getRating() {
			return rating;
		}
		public void setRating(double rating) {
			this.rating = rating;
		}
		public int getRatingCount() {
			return ratingCount;
		}
		public void setRatingCount(int ratingCount) {
			this.ratingCount = ratingCount;
		}
		public Product(String name, String description, double price, int stock) {
	        this.name = name;
	        this.description = description;
	        this.price = price;
	        this.stock = stock;
	    }


	    
}
