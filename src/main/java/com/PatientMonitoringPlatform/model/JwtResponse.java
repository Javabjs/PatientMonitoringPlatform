package com.PatientMonitoringPlatform.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class JwtResponse {
	
	private String jwtToken;
	
	private String email;

}
