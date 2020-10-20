package com.mars.india.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mars.india.domain.Address;
@Component
public class AddressValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Address.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Address address = (Address)target;
		Integer street_no=address.getStreet_no();
		String street_name = address.getStreet_name();
		String city=address.getCity();
		String country= address.getCountry();
		
		if(null==street_no){
			errors.rejectValue("street_no","address.street_no.empty");
		}
		if(null==street_name || "".equals(street_name)){
			errors.rejectValue("street_name","address.street_name.empty");
		}
		if(null==city || "".equals(city)){
			errors.rejectValue("city","address.city.empty");
		}
		if(null==country || "".equals(country)){
			errors.rejectValue("country","address.country.empty");
		}
	
	}
}