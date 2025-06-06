package com.PatientMonitoringPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication 
@ComponentScan(basePackages = "com.PatientMonitoringPlatform")
public class PatientMonitoringPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientMonitoringPlatformApplication.class, args);
	}

}
