package com.rest.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservice.model.Country;
import com.rest.webservice.service.CountryService;
import com.rest.webservice.service.exception.CountryNotFoundException;

@RestController
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@GetMapping("/country/india")
	public Country getCountryIndia() {
		ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("country.xml");
		Country country = context1.getBean("in", Country.class);
		return country;
	}
	
	@GetMapping("/countries")
    public List<Country> getAllCountries(){
		ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("country.xml");
		List<Country> countries= context1.getBean("countryList", java.util.ArrayList.class);
		return countries;
    }
	
	@GetMapping("/countries/{code}")
    public Country getAllCountry(@PathVariable String code) throws CountryNotFoundException{
		return countryService.getCountry(code);
		
    }
}
