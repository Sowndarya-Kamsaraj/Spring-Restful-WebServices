package com.rest.webservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.rest.webservice.model.Country;
import com.rest.webservice.service.exception.CountryNotFoundException;
@Service
public class CountryService {

	public Country getCountry(String code) throws CountryNotFoundException{
		ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("country.xml");
		List<Country> countries= context1.getBean("countryList", java.util.ArrayList.class);
////		for (Country country : countries) {
////			System.out.println(country);
////	        if (country.getCode().equalsIgnoreCase(code)) {
////	            return country;
//	        }
//	    }
//	    return null;
		Optional<Country> country=countries.stream()
				.filter(i->i.getCode().equalsIgnoreCase(code))
				.findFirst();
		
		if(country.isPresent()) {
		return country.get();
			}
		else {
		 throw new CountryNotFoundException();
		}
		}
}
