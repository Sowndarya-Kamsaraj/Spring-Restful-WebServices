package com.rest.webservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rest.webservice.model.Country;
import com.rest.webservice.service.CountryService;
import com.rest.webservice.service.exception.CountryNotFoundException;

@RestController
@RequestMapping("/countries")
public class CountryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);
	
	@Autowired
	CountryService countryService;
	
	@GetMapping("/india")
	public Country getCountryIndia() {
		ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("country.xml");
		Country country = context1.getBean("in", Country.class);
		return country;
	}
	
	@GetMapping
    public List<Country> getAllCountries(){
		ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("country.xml");
		List<Country> countries= context1.getBean("countryList", java.util.ArrayList.class);
		return countries;
    }
	
	@GetMapping("/{code}")
    public Country getAllCountry(@PathVariable String code) throws CountryNotFoundException{
		return countryService.getCountry(code);
		
    }
	
	@PostMapping
	public Country addCountry(@RequestBody @Valid Country country) {
		LOGGER.info("************START*****************");
		LOGGER.info(country.toString());
		return country;
	}
	
}
