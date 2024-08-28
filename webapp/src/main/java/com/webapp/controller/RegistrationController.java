package com.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.dto.RegistrationDto;
import com.webapp.entity.Registration;
import com.webapp.service.RegistrationService;
import com.webapp.util.EmailService;

@Controller
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private EmailService emailService;
	// http://localhost:8080/view-registration-page
	// handler methods

	@RequestMapping("/view-registration-page")
	public String viewRegistrationPage() {
		return "registration";
	}

//	@RequestMapping("/saveReg")
//	public String addRegistration(@ModelAttribute Registration registration) {
//		registrationService.addRegistration(registration);
//		return "registration";
//	}
//	@RequestMapping("/saveReg")
//	public String addRegistration(
//			@RequestParam("xyz")String firstName,
//			@RequestParam("lastName")String lastName,
//			@RequestParam("email")String email,
//			@RequestParam("mobile")long mobile,
//			Model model
//			) {
//		//registrationService.addRegistration(registration);
////		System.out.println(firstName);
////		System.out.println(lastName);
////		System.out.println(email);
////		System.out.println(mobile);
//		Registration registration=new Registration();
//		registration.setFirstName(firstName);
//		registration.setLastName(firstName);
//		registration.setEmail(email);
//		registration.setMobile(mobile);
//		registrationService.addRegistration(registration);
//		model.addAttribute("information","some data..");
//		model.addAttribute("msg","Record is saved!!");
//		return "registration";
//	}
	@RequestMapping("/saveReg")
	public String addRegistration(RegistrationDto dto) {
//	        System.out.println(dto.getFirstName());
//	        System.out.println(dto.getLastName());
//	        System.out.println(dto.getEmail());
//	        System.out.println(dto.getMobile());
//		    return "registration";
		Registration registration = new Registration();
		registration.setFirstName(dto.getFirstName());
		registration.setLastName(dto.getLastName());
		registration.setEmail(dto.getEmail());
		registration.setMobile(dto.getMobile());
		registrationService.addRegistration(registration);
		emailService.sendEmail(dto.getEmail(),"Test", "Test");
		return "registration";
	}

	@RequestMapping("/list-all")
	public String listRegistration(Model model) {
		List<Registration> registrations = registrationService.findAllRegistrations();
		model.addAttribute("registrations", registrations);
		return "list_registration";
	}

	@RequestMapping("/delete")
	public String deleteRegistration(@RequestParam("id") long id, ModelMap model) {
		registrationService.deleteRegistration(id);

		List<Registration> registrations = registrationService.findAllRegistrations();
		model.addAttribute("registrations", registrations);
		return "list_registration";
	}

	@RequestMapping("/get-by-id")
	public String getRegistrationById(@RequestParam("id") long id, ModelMap model) {
		Registration registration = registrationService.getRegistrationById(id);
		model.addAttribute("registration", registration);

		return "update_registration";
	}

	@RequestMapping("/updateReg")
	public String getRegistrationById(RegistrationDto dto, ModelMap model) {
		Registration registration = new Registration();
		registration.setId(dto.getId());
		registration.setFirstName(dto.getFirstName());
		registration.setLastName(dto.getLastName());
		registration.setEmail(dto.getEmail());
		registration.setMobile(dto.getMobile());
		registrationService.updateRegistration(registration);

		List<Registration> registrations = registrationService.findAllRegistrations();
		model.addAttribute("registrations", registrations);
		return "list_registration";
	}

}
