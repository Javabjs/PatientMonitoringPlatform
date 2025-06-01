package com.PatientMonitoringPlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.PatientMonitoringPlatform.model.JwtRequest;
import com.PatientMonitoringPlatform.model.JwtResponse;
import com.PatientMonitoringPlatform.model.User;

public interface UserService {
	User createUser(User user);
	Optional<User> getUserByUuid(UUID uuid );
	List<User> getAllUser();
	String deleteByUuid(UUID uuid);
	User updateByUuid(UUID uuid, User user);
	String registerUser(User user);
	JwtResponse loginUser(JwtRequest jwtRequest);
	


}
