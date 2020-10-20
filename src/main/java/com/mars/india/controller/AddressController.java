package com.mars.india.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import com.mars.india.domain.Address;
import com.mars.india.service.AddressService;
import com.mars.india.validator.AddressValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/main/address")
public class AddressController {

	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="addressService")
	private AddressService addressService;
	@Autowired(required=true)
	AddressValidator addressValidator;
   
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(@RequestParam("id") Integer personId, Model model) {
    	logger.debug("Received request to show add page");
    
    	// Prepare model object
    	Address address = new Address();
    	
    	// Add to model
    	model.addAttribute("personId", personId);
    	model.addAttribute("addressAttribute", address);

    	return "add-Address";
	}
 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@RequestParam("id") Integer personId,@ModelAttribute("addressAttribute") Address address,BindingResult result, Model model) {
    	logger.debug("Received request to add new record");
    	model.addAttribute("personId", personId);
    	model.addAttribute("addressAttribute",address);
    	addressValidator.validate(address, result);
    	if(result.hasErrors()){
    		return "add-Address";
    	}else{
    		// Delegate to service
    		addressService.add(personId, address);
    	}
		// Redirect to url
		return "redirect:/mars/main/record/list";
	}
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer addressId) {
    	// Delegate to service
		addressService.delete(addressId);
		// Redirect to url
		return "redirect:/mars/main/record/list";
	}
   
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("pid") Integer personId, @RequestParam("aid") Integer addressId, Model model) {

    	Address existingAddress = addressService.get(addressId);
    	// Add to model
    	model.addAttribute("personId", personId);
    	model.addAttribute("addressAttribute", existingAddress);

    	return "edit-Address";
	}
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer addressId,@RequestParam("id") Integer personId ,@ModelAttribute("addressAttribute") Address address,
    		BindingResult result, Model model) {
		
		// Assign id
		address.setId(addressId);
		model.addAttribute("personId", personId);
		model.addAttribute("addressAttribute",address);
    	addressValidator.validate(address, result);
    	if(result.hasErrors()){
    		return "edit-Address";
    	}else{
    		// Delegate to service
    		addressService.edit(address);
    	}
		// Redirect to url
		return "redirect:/mars/main/record/list";
	}

	public AddressValidator getAddressValidator() {
		return addressValidator;
	}

	public void setAddressValidator(AddressValidator addressValidator) {
		this.addressValidator = addressValidator;
	}
}