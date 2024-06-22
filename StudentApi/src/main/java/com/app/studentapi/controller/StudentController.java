package com.app.studentapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.studentapi.constants.ResponseKey;
import com.app.studentapi.model.Student;
import com.app.studentapi.service.StudentService;
import com.example.demo.model.Product;
import com.example.demo.model.Review;

@CrossOrigin
@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping("/greet/{name}")
	public ResponseEntity<?> greet(@PathVariable("name") String name, @RequestHeader(name="Accept-Lanuage",required=false)Locale locale){
		String messages = messageSource.getMessage("greeting.message", new Object[] {"name"}, locale);
		HashMap<String, String> responseBody = new HashMap<>();
		responseBody.put(ResponseKey.MESSAGE, messages);
		
		return new ResponseEntity<>(responseBody,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> findAll(@RequestHeader(name="Accept-Lanuage",required=false)Locale locale) {
		try {
			List<Student> productsList = studentService.findAll();
			if(productsList.isEmpty()) {
				HashMap< String, String> data = new HashMap<>();
				data.put(ResponseKey.MESSAGE, messageSource.getMessage("error.noproductavailable", null,locale));
				return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
			}else {
				HashMap<String, Object> data = new HashMap<>();
				data.put(ResponseKey.COUNT, studentService.countAll());
				data.put(ResponseKey.PRODUCTS, productsList);
				return new ResponseEntity<>(data, HttpStatus.OK);
			}
		} catch (Exception e) {
			HashMap< String, String> data = new HashMap<>();
			data.put(ResponseKey.MESSAGE,messageSource.getMessage("error.exception", null, locale));
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader(name="Accept-Lanuage",required=false)Locale locale){
		HashMap< Object, Object> data = new HashMap<>();
		try {
			Optional<Product> productOptional = studentService.getbyId(id);
			if(productOptional.isPresent()) {
				Product product = productOptional.get();
				return new ResponseEntity<>(product,HttpStatus.OK);
			}
			else {
				data.put(ResponseKey.MESSAGE,messageSource.getMessage("error.productnotfound", null, locale));
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			data.put(ResponseKey.MESSAGE,messageSource.getMessage("error.exception", null, locale));
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody Product product,@RequestHeader(name="Accept-Lanuage",required=false)Locale locale) {
		try {
			Product savedProduct = studentService.save(product);
			return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
		} catch (Exception e) {
			HashMap< String, String> data = new HashMap<>();
			data.put(ResponseKey.MESSAGE,messageSource.getMessage("error.exception", null, locale));
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable int id,@RequestHeader(name="Accept-Lanuage",required=false)Locale locale){
		HashMap< Object, Object> data = new HashMap<>();
		try {
			Optional<Product> productOptional = studentService.getbyId(id);
			if(productOptional.isPresent()) {
				studentService.remove(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				data.put(ResponseKey.MESSAGE, messageSource.getMessage("error.productnotfound", null, locale));
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			data.put(ResponseKey.MESSAGE,messageSource.getMessage("error.exception", null, locale));
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/byName/{name}")
	public ResponseEntity<?> findByName(@PathVariable String name,@RequestHeader(name="Accept-Lanuage",required=false)Locale locale){
		HashMap< Object, Object> data = new HashMap<>();
		try {
			
			List<Product> productList = studentService.findByName(name);
			return new ResponseEntity<>(productList,HttpStatus.OK);
			
		} catch (Exception e) {
			data.put(ResponseKey.MESSAGE,messageSource.getMessage("error.exception", null, locale));
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/{id}/reviews")
	public ResponseEntity<?> findAllReviewsByProduct(@PathVariable int id,@RequestHeader(name="Accept-Lanuage",required=false)Locale locale){
		HashMap< Object, Object> data = new HashMap<>();
		try {
			Optional<Product> productOptional = studentService.getbyId(id);
			if(productOptional.isPresent()) {
				Product product = productOptional.get();
				List<Review> reviews = product.getReviews();
				return new ResponseEntity<>(reviews ,HttpStatus.OK);
			}
			else {
				data.put(ResponseKey.MESSAGE, messageSource.getMessage("error.productnotfound", null, locale));
				return new ResponseEntity<>(data,HttpStatus.OK);
			}
			
		} catch (Exception e) {
			data.put(ResponseKey.MESSAGE,messageSource.getMessage("error.exception", null, locale));
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
