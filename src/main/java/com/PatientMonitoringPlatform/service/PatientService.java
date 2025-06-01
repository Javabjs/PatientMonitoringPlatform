package com.PatientMonitoringPlatform.service;

import com.PatientMonitoringPlatform.model.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientService {
    Patient createPatient(Patient patient);
    Optional<Patient> getPatientByUuid(UUID uuid);
    List<Patient> getAllPatient();
    String deleteByUuid(UUID uuid);
    Patient updateByUuid(UUID uuid, Patient patient);
	
}
