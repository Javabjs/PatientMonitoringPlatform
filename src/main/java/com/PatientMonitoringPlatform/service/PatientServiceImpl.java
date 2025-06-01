package com.PatientMonitoringPlatform.service;

import com.PatientMonitoringPlatform.model.Patient;
import com.PatientMonitoringPlatform.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository pRepository;

    @Override
    public Patient createPatient(Patient patient) {
       return pRepository.save(patient);
    }

    @Override
    public Optional<Patient> getPatientByUuid(UUID uuid) {
        return pRepository.findById(uuid);
    }

    @Override
    public List<Patient> getAllPatient() {
        return pRepository.findAll();
    }

    @Override
    public String deleteByUuid(UUID uuid) {
        pRepository.deleteById(uuid);
        return "Patient with UUID " + uuid + " deleted successfully.";
    }

    @Override
    public Patient updateByUuid(UUID uuid, Patient patient) {
        Optional<Patient>pt=pRepository.findById(uuid);
        Patient p=pt.get();
        p.setName(patient.getName());
        p.setAge(patient.getAge());
        p.setStatus(patient.getStatus());
        return pRepository.save(p);
            
}

	
	
}