package com.rest.webservice.restfulwebservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rest.webservice.model.Country;

//import com.cognizant.springlearn.Country;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
@ComponentScan(basePackages="com.rest.webservice")
public class RestfulWebServiceApplication {
	private static final Logger LOGGER=(Logger) LoggerFactory.getLogger(RestfulWebServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServiceApplication.class, args);
		System.out.println("App started");
		displayDate();
		displayCountry();
		displayCountries();
	}
	public static void displayDate()
	{
		LOGGER.info("Start");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
		SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
		try {
			Date d=format.parse("31/12/2018");
			LOGGER.debug(d.toString());
			
		}catch(ParseException e) {
			e.printStackTrace();
		}
		LOGGER.info("End");
	}
	public static void displayCountry()
	{
		
		ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("country.xml");
		Country country = context1.getBean("in", Country.class);

		LOGGER.debug("Country : {}", country.toString());
		Country anotherCountry = context1.getBean("in", Country.class);
	}
	public static void displayCountries()
	{
		ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("country.xml");
		
		List<Country> countries = context1.getBean("countryList", java.util.ArrayList.class);

		//LOGGER.debug("Country : {}", countries.toString());
		LOGGER.debug("List:{}",countries);
		//Country anotherCountry = context1.getBean("in", Country.class);
		
	
	}
}
