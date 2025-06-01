package com.PatientMonitoringPlatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.PatientMonitoringPlatform.model.JwtRequest;
import com.PatientMonitoringPlatform.model.JwtResponse;
import com.PatientMonitoringPlatform.security.jwtutils.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtHelper.generateJwtToken(authentication);

        JwtResponse response = new JwtResponse();
        response.setJwtToken(token);
        response.setEmail(request.getEmail());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
