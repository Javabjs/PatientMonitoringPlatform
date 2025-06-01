package com.PatientMonitoringPlatform.Controller;

import com.PatientMonitoringPlatform.model.Patient;
import com.PatientMonitoringPlatform.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:3000") 
public class PatientController {

    @Autowired
    private PatientService pService;

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return pService.createPatient(patient);
    }

    @GetMapping("/{uuid}")
    public Optional<Patient> getPatientByUuid(@PathVariable String uuid) {
        return pService.getPatientByUuid(UUID.fromString(uuid));
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return pService.getAllPatient();
    }

    @PutMapping("/{uuid}")
    public Patient updatePatient(@PathVariable String uuid, @RequestBody Patient patient) {
        return pService.updateByUuid(UUID.fromString(uuid), patient);
    }

    @DeleteMapping("/{uuid}")
    public String deletePatient(@PathVariable String uuid) {
        return pService.deleteByUuid(UUID.fromString(uuid));
    }
}
