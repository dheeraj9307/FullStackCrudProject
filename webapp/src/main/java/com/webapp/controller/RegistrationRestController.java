package com.webapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.dto.RegistrationDto;
import com.webapp.entity.Registration;
import com.webapp.exception.ResourceNotFound;
import com.webapp.repository.RegistrationRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@RestController
@RequestMapping("/api/marketing")
public class RegistrationRestController {
  //http://localhost:8080/api/marketing
	@Autowired
	private RegistrationRepository registrationRepository;
	
//	@GetMapping
//	public List<Registration> getAllReg(){
//		
//		List<Registration> registrations = registrationRepository.findAll();
//		return registrations;
//	}
	
	//Same way we can also do in @GetMapping
	

	@GetMapping
	public ResponseEntity<List<Registration>> getAllReg(){
		
		List<Registration> registrations = registrationRepository.findAll();
		return  new ResponseEntity<>(registrations,HttpStatus.OK);
	}
	
	//http://localhost:8080/api/marketing?id=1
//	Same way we can also do in delete a record
//	@DeleteMapping
//	public ResponseEntity<String> delete(@RequestParam long id) {
//		registrationRepository.deleteById(id);
//		return ResponseEntity.ok("Registration is deleted");
//	}
	//one way is use try catch block to handle exception handling
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam long id){
		java.util.Optional<Registration> byId=registrationRepository.findById(id);
		
		if(byId.isPresent()) {
			registrationRepository.deleteById(id);	
		}else {
			try {
				throw new ResourceNotFound();
			} catch (Exception e) {
				System.out.println(1);
				return new ResponseEntity<>("Could not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>("Registration is deleted",HttpStatus.OK);
	}
	
	
	
//	@PostMapping
//    public void saveRegistration(@RequestBody Registration registration) {
//		registrationRepository.save(registration);
//    }
	//i want to see response at postman
	
//	@PostMapping
//    public ResponseEntity<String> saveRegistration(@RequestBody Registration registration) {
//		registrationRepository.save(registration);
//		return new ResponseEntity<>("Registration Created",HttpStatus.CREATED);
//    }
	
	//I can also send a response back like this
	@PostMapping
    public ResponseEntity<Registration> saveRegistration(@RequestBody Registration registration) {
		Registration savedReg = registrationRepository.save(registration);
		return new ResponseEntity<>(savedReg,HttpStatus.CREATED);
    }
	
//	@PutMapping
//    public void updateRegistration(@RequestBody RegistrationDto dto) {
//		Registration reg=new Registration();
//		reg.setId(dto.getId());
//		reg.setFirstName(dto.getFirstName());
//		reg.setLastName(dto.getLastName());
//		reg.setEmail(dto.getEmail());
//		reg.setMobile(dto.getMobile());
//		registrationRepository.save(reg);
//    }
	
	//method 2 to update  a record
	//http://localhost:8080/api/marketing/3
	@PutMapping("/{id}")
    public void updateRegistration(@RequestBody RegistrationDto dto,@PathVariable long id) {
		Registration reg=new Registration();
		reg.setId(id);
		reg.setFirstName(dto.getFirstName());
		reg.setLastName(dto.getLastName());
		reg.setEmail(dto.getEmail());
		reg.setMobile(dto.getMobile());
		registrationRepository.save(reg);
    }
	
}

