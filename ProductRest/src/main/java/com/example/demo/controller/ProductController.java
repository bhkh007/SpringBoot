package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;


/*@Controller
@ResponseBody*/

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public List<Product> findAll() {
		
		return productService.findAll();
	}
	
	
	@PostMapping("/")
	public Product save(@RequestBody Product product) {
		Product savedProduct =  productService.save(product);
		
		return savedProduct;
	}
}
