package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	
	public Product  save(Product product) {
		
		return productRepository.save(product);
	}
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public Optional<Product> getbyId(int id){
		Optional<Product> op  = productRepository.findById(id);
		return op;
	}
	
	public void remove(int id) {
		productRepository.deleteById(id);
	}
	
	public long countAll() {
		return productRepository.count();
	}
}
