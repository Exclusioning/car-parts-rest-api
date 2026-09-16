package com.mycompany.carpartsapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTests{
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetCart() throws Exception{
		mockMvc.perform(get("/api/cart")
			.param("sessionId", "MillsZack")
			.with(oauth2Login().attributes(a -> a.put("name", "MillsZack"))))
			.andExpect(status().isOk());
	}

	@Test
	void testAddToCart() throws Exception{
		mockMvc.perform(post("/api/cart/1/2")
			.param("sessionId", "MillsZack")
                	.with(oauth2Login().attributes(a -> a.put("name", "MillsZack"))))
			.andExpect(status().isOk());
	}
	
	@Test
	void testDeleteFromCart() throws Exception{
		mockMvc.perform(delete("/api/cart/1")
		.param("sessionId", "MillsZack")
		.with(oauth2Login().attributes(a -> a.put("name", "MillsZack"))))
		.andExpect(status().isOk());
	}
	
	@Test
	void testUpdateCart() throws Exception{
		mockMvc.perform(put("/api/cart/1/6")
		.param("sessionId", "MillsZack")
		.with(oauth2Login().attributes(a -> a.put("name", "MillsZack"))))
		.andExpect(status().isOk());
	}

	@Test
	void getNewCart() throws Exception{
		mockMvc.perform(get("/api/cart")
		.param("sessionId", "newUser")
		.with(oauth2Login().attributes(a -> a.put("name", "newCart"))))
		.andExpect(status().isOk());
	}

}
