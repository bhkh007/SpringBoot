package com.app.studentapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.studentapi.model.Student;
import com.app.studentapi.repository.StudentRepository;
import com.example.demo.model.Product;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
public Student  save(Student product) {
		
		return studentRepository.save(product);
	}
	
	public List<Student> findAll() {
		return studentRepository.findAll();
	}
	
	public Optional<Student> getbyId(int id){
		Optional<Student> op  = studentRepository.findById(id);
		return op;
	}
	
	public void remove(int id) {
		studentRepository.deleteById(id);
	}
	
	public long countAll() {
		return productRepository.count();
	}
	
	public List<Product> findByName(String name){
		return productRepository.findByName(name);
	}

}
