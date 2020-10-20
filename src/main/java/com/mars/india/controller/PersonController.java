package com.mars.india.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;

import com.mars.india.validator.PersonValidator;
import com.mars.india.domain.Person;
import com.mars.india.dto.PersonDTO;
import com.mars.india.service.AddressService;
import com.mars.india.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/main/record")
public class PersonController {

	protected static Logger logger = Logger.getLogger("controller");
	@Autowired
	PersonValidator personValidator;
	@Resource(name="personService")
	private PersonService personService;
	
	@Resource(name="addressService")
	private AddressService addressService;
	
	/**
	 * Retrieves the "Records" page
	 */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRecords(Model model) {
    	logger.debug("Received request to show records page");
    	// Retrieve all persons
    	List<Person> persons = personService.getAll();
    	// Prepare model object
    	List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();
    	
    	for (Person person: persons) {
    		// Create new data transfer object
    		PersonDTO dto = new PersonDTO();
			dto.setId(person.getId());
			dto.setFirstName(person.getFirstName());
			dto.setLastName(person.getLastName());
			dto.setAddresses(addressService.getAll(person.getId()));
			// Add to model list
			personsDTO.add(dto);
    	}
    	
    	// Add to model
    	model.addAttribute("persons", personsDTO);

    	// This will resolve to /WEB-INF/jsp/records.jsp
		return "records";
	}
    
    /**
     *  Retrieves the "Add New Record" page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
    	logger.debug("Received request to show add page");
    
    	// Create new Person and add to model
    	model.addAttribute("personAttribute", new Person());

    	// This will resolve to /WEB-INF/jsp/add-record.jsp
    	return "add-record";
	}
 
    /**
     * Adds a new record
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@ModelAttribute("personAttribute") Person person, BindingResult result, Model model) {
		logger.debug("Received request to add new record");
		model.addAttribute("personAttribute",person);
		personValidator.validate(person, result);
		// Delegate to service
		if(result.hasErrors()){
	    	  return "add-record";
	      }else{
	    	  personService.add(person);
	      }
		// Redirect to url
		return "redirect:/mars/main/record/list";
	}
    
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer personId) {
    	logger.debug("Received request to delete record");
    	// Delete person
		personService.delete(personId);
		// Redirect to url
		return "redirect:/mars/main/record/list";
	}
    
    /**
     * Retrieves the "Edit Existing Record" page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("id") Integer personId, Model model) {
    	logger.debug("Received request to show edit page");
    	
    	// Retrieve person by id
    	Person existingPerson = personService.get(personId);

    	// Add to model
    	model.addAttribute("personAttribute", existingPerson);

    	// This will resolve to /WEB-INF/jsp/edit-record.jsp
    	return "edit-record";
	}
 
    /**
     * Edits an existing record
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer personId,@ModelAttribute("personAttribute") Person person, BindingResult result, Model model) {
		logger.debug("Received request to edit existing person");
		
		// Assign id
		person.setId(personId);
		model.addAttribute("personAttribute",person);
		personValidator.validate(person, result);
		if(result.hasErrors()){
			return "edit-record";
		}else{
			// Delegate to service
			personService.edit(person);
		}
		// Redirect to url
		return "redirect:/mars/main/record/list";
	}

	public PersonValidator getPersonValidator() {
		return personValidator;
	}

	public void setPersonValidator(PersonValidator personValidator) {
		this.personValidator = personValidator;
	}
    
}