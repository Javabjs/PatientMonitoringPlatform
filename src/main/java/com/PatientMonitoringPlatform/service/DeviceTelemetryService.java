package com.PatientMonitoringPlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.PatientMonitoringPlatform.model.Device;
import com.PatientMonitoringPlatform.model.DeviceTelemetry;

public interface DeviceTelemetryService {
	DeviceTelemetry createTelemetry(DeviceTelemetry devicetelemetry);
	Optional<DeviceTelemetry> getDeviceByUuid(UUID uuid);
	List<DeviceTelemetry> getAll();
	String deleteByUuid(UUID uuid);
	DeviceTelemetry updateByUuid(UUID uuid, DeviceTelemetry devicetelemetry);
	Device findByMacAddress(String macAddress);
	
}
