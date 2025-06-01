package com.PatientMonitoringPlatform.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PatientMonitoringPlatform.model.Device;
import com.PatientMonitoringPlatform.model.DeviceTelemetry;

public interface DeviceTelemetryRepository extends JpaRepository<DeviceTelemetry, UUID> {
	List<DeviceTelemetry> findByDeviceId(UUID deviceId);
	Optional<Device> findByMacAddress(String macAddress); 

	





}
