package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String reviewGivenBy;
	private String content;
	
	@ManyToOne
	private Product product;
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReviewGivenBy() {
		return reviewGivenBy;
	}
	public void setReviewGivenBy(String reviewGivenBy) {
		this.reviewGivenBy = reviewGivenBy;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewGivenBy=" + reviewGivenBy + ", content=" + content + "]";
	}
	
	
}
