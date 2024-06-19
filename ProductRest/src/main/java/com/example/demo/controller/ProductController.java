package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.constants.ResponseKey;
import com.example.demo.constants.ResponseMessage;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;


/*@Controller	
@ResponseBody*/


@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		try {
			List<Product> productsList = productService.findAll();
			if(productsList.isEmpty()) {
				HashMap< String, String> data = new HashMap<>();
				data.put(ResponseKey.MESSAGE, ResponseMessage.NO_PRODUCT_FOUND);
				return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
			}else {
				HashMap<String, Object> data = new HashMap<>();
				data.put(ResponseKey.COUNT, productService.countAll());
				data.put(ResponseKey.PRODUCTS, productsList);
				return new ResponseEntity<>(data, HttpStatus.OK);
			}
		} catch (Exception e) {
			HashMap< String, String> data = new HashMap<>();
			data.put(ResponseKey.MESSAGE,ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id){
		HashMap< Object, Object> data = new HashMap<>();
		try {
			Optional<Product> productOptional = productService.getbyId(id);
			if(productOptional.isPresent()) {
				Product product = productOptional.get();
				return new ResponseEntity<>(product,HttpStatus.OK);
			}
			else {
				data.put(ResponseKey.MESSAGE, ResponseMessage.NO_PRODUCT_FOUND_BY_ID);
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			data.put(ResponseKey.MESSAGE,ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody Product product) {
		try {
			Product savedProduct = productService.save(product);
			return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
		} catch (Exception e) {
			HashMap< String, String> data = new HashMap<>();
			data.put(ResponseKey.MESSAGE,ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable int id){
		HashMap< Object, Object> data = new HashMap<>();
		try {
			Optional<Product> productOptional = productService.getbyId(id);
			if(productOptional.isPresent()) {
				productService.remove(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				data.put(ResponseKey.MESSAGE, ResponseMessage.NO_PRODUCT_FOUND_BY_ID);
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			data.put(ResponseKey.MESSAGE,ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/byName/{name}")
	public ResponseEntity<?> findByName(@PathVariable String name){
		HashMap< Object, Object> data = new HashMap<>();
		try {
			
			List<Product> productList = productService.findByName(name);
			return new ResponseEntity<>(productList,HttpStatus.OK);
			
		} catch (Exception e) {
			data.put(ResponseKey.MESSAGE,ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
