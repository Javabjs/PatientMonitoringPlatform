package com.PatientMonitoringPlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.PatientMonitoringPlatform.model.JwtRequest;
import com.PatientMonitoringPlatform.model.JwtResponse;
import com.PatientMonitoringPlatform.model.User;
import com.PatientMonitoringPlatform.repository.UserRepository;
import com.PatientMonitoringPlatform.security.jwtutils.JwtHelper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtHelper jwtHelper;


    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository uRepository;

    @Override
    public String registerUser(User user) {
        // ✅ Encode password before saving
        user.setPassword(encoder.encode(user.getPassword()));
        uRepository.save(user);
        return "User registered successfully";
    }
    
    @Override
    public JwtResponse loginUser(JwtRequest jwtRequest) {
        // Authenticate the credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtRequest.getEmail(),
                        jwtRequest.getPassword()
                )
        );

        // Generate JWT token
        String token = jwtHelper.generateJwtToken(authentication);

        // Create and return response
        JwtResponse response = new JwtResponse();
        response.setJwtToken(token);
        response.setEmail(jwtRequest.getEmail());

        return response;
    }


    @Override
    public User createUser(User user) {
        // ✅ Also encode password here
        user.setPassword(encoder.encode(user.getPassword()));
        return uRepository.save(user);
    }


    @Override
    public Optional<User> getUserByUuid(UUID uuid) {
        return uRepository.findById(uuid);
    }

    @Override
    public List<User> getAllUser() {
        return uRepository.findAll();
    }

    @Override
    public String deleteByUuid(UUID uuid) {
        uRepository.deleteById(uuid);
        return "User deleted successfully";
    }

    @Override
    public User updateByUuid(UUID uuid, User user) {
        Optional<User> usr = uRepository.findById(uuid);
        if (usr.isPresent()) {
            User u = usr.get();
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            u.setPhone_no(user.getPhone_no());
            u.setComplete_address(user.getComplete_address());
            u.setDistrict(user.getDistrict());
            u.setState(user.getState());
            u.setCountry(user.getCountry());
            u.setPincode(user.getPincode());
            u.setUpdatedAt(user.getUpdatedAt());
            return uRepository.save(u);
        } else {
            throw new RuntimeException("User not found with UUID: " + uuid);
        }
    }
}
