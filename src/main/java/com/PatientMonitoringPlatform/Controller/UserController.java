package com.PatientMonitoringPlatform.Controller;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PatientMonitoringPlatform.model.JwtRequest;
import com.PatientMonitoringPlatform.model.JwtResponse;
import com.PatientMonitoringPlatform.model.User;
import com.PatientMonitoringPlatform.service.UserService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")

public class UserController {
	@Autowired
	private UserService uService;
	
	@PostMapping("/register")
	 public ResponseEntity<String> registerUser(@RequestBody User user) {
	        String result = uService.registerUser(user);
	        return ResponseEntity.ok(result);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
	    JwtResponse response = uService.loginUser(request);
	    return ResponseEntity.ok(response); // ✅ this returns the token to the frontend
	}

	
	
	
	@GetMapping("/test")
    public String testEndpoint() {
        return "User Controller is working!";
    }
	@PostMapping
	public User createUser(@RequestBody User user) {
		return uService.createUser(user) ;
	
	}
	
	@GetMapping("/{uuid}")
	public Optional<User> getUserByUuid(@PathVariable String uuid){
		return uService.getUserByUuid(UUID.fromString(uuid));
		
	}
	
	@GetMapping
	public List<User> getAllUser(){
		return uService.getAllUser();
		
	}
	
	@DeleteMapping("/{uuid}")
	public String deleteByUuid(@PathVariable String uuid) {
		return uService.deleteByUuid(UUID.fromString(uuid));
		
	}
	
	@PutMapping("/{uuid}")
	public User updateByUuid(@PathVariable String uuid,@RequestBody User user) {
		return uService.updateByUuid(UUID.fromString(uuid), user);
		
	}

}


