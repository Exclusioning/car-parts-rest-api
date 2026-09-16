package com.mycompany.carpartsapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
class PartControllerTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser
	void testGetParts() throws Exception {
		mockMvc.perform(get("/api/parts")
		.with(oauth2Login().attributes(a -> a.put("name", "MillsZack"))))	
			.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	void testAddPart() throws Exception {
		mockMvc.perform(post("/api/parts")
			.param("name", "Exhaust")
			.param("price", "936.78")
			.param("quantity", "10")
			.with(oauth2Login().attributes(a -> a.put("name", "MillsZack"))))
			.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	void testAddPart2() throws Exception {
		mockMvc.perform(post("/api/parts")
			.param("name", "O2 Sensor")
			.param("price", "28.97")
			.param("quantity", "32")
			.with(oauth2Login().attributes(a -> a.put("name", "MillsZack"))))
			.andExpect(status().isOk());
	}
}
