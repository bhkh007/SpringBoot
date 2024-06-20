package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;



//@SpringBootTest
@WebMvcTest
class ProductRestApplicationTests {

	@Autowired
	private MockMvc mockMvc; //to mock request response data
	
	@Autowired
	private ObjectMapper mapper;  // for json <-->java object
	
	@MockBean
	private ProductService productService;  // not actual but mocked object
	
	@Test
	public void getProductsListTest() {
		List<Product> list = new ArrayList<>();
		Product p1 = new Product();
		p1.setId(101);
		p1.setName("keyboard");
		p1.setPrice(1000);
		p1.setQuantity(10);
		
		Product p2 = new Product();
		p2.setId(102);
		p2.setName("usb drive");
		p2.setPrice(450);
		p2.setQuantity(15);
		
		list.add(p1);
		list.add(p2);
		
		when(productService.findAll()).thenReturn(list);
		when(productService.countAll()).thenReturn((long)list.size());
		
		try {
			ResultActions response = mockMvc.perform(get("/products/"));
			
			response
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.count", is(list.size())));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void getByIdTest() {
		Product p1 = new Product();
		p1.setId(101);
		p1.setName("keyboard");
		p1.setPrice(1000);
		p1.setQuantity(10);
		
		when(productService.getbyId(101)).thenReturn(Optional.of(p1));
		try {
		ResultActions response =	mockMvc.perform(get("/products/{id}",101));
		response.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.id", is(p1.getId())))
		.andExpect(jsonPath("$.name", is(p1.getName())))
		.andExpect(jsonPath("$.price", is(Double.valueOf(p1.getPrice()) )))
		.andExpect(jsonPath("$.quantity", is(p1.getQuantity())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
	
	
	/*@Autowired
	Calculator calcii;
	
	@Test
	void sumTest() {
		int expectedResult = 50;
		int actualresult = calcii.sum(25 ,25);
		
		assertThat(expectedResult).isEqualTo(actualresult);
	
	}
	@Test
	void compareTest() {
		boolean actualCompare = calcii.compare(20, 10);
		assertThat(actualCompare).isTrue();	
		
	}*/


