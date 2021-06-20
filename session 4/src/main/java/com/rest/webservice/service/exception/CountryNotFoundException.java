package com.rest.webservice.service.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country Not found")
public class CountryNotFoundException extends Exception{
	
	

}
