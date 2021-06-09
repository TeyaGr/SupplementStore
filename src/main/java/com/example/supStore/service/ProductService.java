package com.example.supStore.service;

import java.util.List;

import com.example.supStore.entity.Product;

public interface ProductService {
 
	Product save(Product product);

	List<Product> findAll();
}
