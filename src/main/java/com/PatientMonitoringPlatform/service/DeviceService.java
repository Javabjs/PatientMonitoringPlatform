package com.PatientMonitoringPlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.PatientMonitoringPlatform.model.Device;

public interface DeviceService {
	 Device createDevice(Device device);
	 Optional<Device> getDeviceByUuid(UUID uuid);
	 List<Device> getAllDevices();
	 String deleteByUuid(UUID uuid);
	 Device updateByUuid(UUID uuid, Device device);

}
