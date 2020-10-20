package com.mars.india.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mars.india.domain.Person;

@Component
public class PersonValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Person.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Person person = (Person)target;
		String firstName = person.getFirstName();
		String lastName = person.getLastName();
		
		if(null==firstName || "".equals(firstName)){
			errors.rejectValue("firstName","person.firstname.empty");
		}
		if(null==lastName || "".equals(lastName)){
			errors.rejectValue("lastName","person.lastname.empty");
		}
	
	}
}