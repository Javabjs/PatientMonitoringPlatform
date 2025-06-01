package com.PatientMonitoringPlatform.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PatientMonitoringPlatform.model.Device;



public interface DeviceRepository extends JpaRepository<Device,UUID> {
    Device findByUuid(String uuid);

	Optional<Device> findByMacAddress(String macAddress); 

}
