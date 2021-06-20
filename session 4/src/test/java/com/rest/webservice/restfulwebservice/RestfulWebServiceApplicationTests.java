package com.rest.webservice.restfulwebservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rest.webservice.controller.CountryController;

@SpringBootTest
@AutoConfigureMockMvc
class RestfulWebServiceApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private CountryController countryController;

	@Test
	void contextLoads() {
		assertNotNull(countryController);
	}

	@Test

	public void testGetCountry() throws Exception {

		ResultActions actions = mvc.perform(get("/countries/india"));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.code").exists());
		actions.andExpect(jsonPath("$.code").value("in"));

	}

	@Test
	public void testGetCountryException() throws Exception {

		ResultActions actions = mvc.perform(get("/countries/az"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Country Not found"));
	}

}
