package com.example.supStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supStore.entity.Product;
import com.example.supStore.repository.ProductRepository;
@Service
public class ProductServiceImpl  implements ProductService {
@Autowired
ProductRepository productRepo;


	public Product save(Product product) {
		// TODO Auto-generated method stub
		return productRepo.save(product);
	}


	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return (List<Product>) productRepo.findAll();
	}

}
