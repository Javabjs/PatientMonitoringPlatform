package com.PatientMonitoringPlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PatientMonitoringPlatform.model.Device;
import com.PatientMonitoringPlatform.model.DeviceTelemetry;
import com.PatientMonitoringPlatform.repository.DeviceRepository;
import com.PatientMonitoringPlatform.repository.DeviceTelemetryRepository;

@Service

public class DeviceTelemetryServiceImpl implements DeviceTelemetryService{
	
	 @Autowired
	 private DeviceTelemetryRepository tRepository;

	 @Autowired
	 private DeviceRepository dRepository;

	@Override
	public DeviceTelemetry createTelemetry(DeviceTelemetry devicetelemetry) {
		Optional<Device> device=dRepository.findById(devicetelemetry.getDevice().getId());
		devicetelemetry.setDevice(device.get());
		return tRepository.save(devicetelemetry);
		
	}

	@Override
	public Optional<DeviceTelemetry> getDeviceByUuid(UUID uuid) {
		return tRepository.findById(uuid);
	}

	@Override
	public List<DeviceTelemetry> getAll() {
		return tRepository.findAll();
	}

	@Override
	public String deleteByUuid(UUID uuid) {
	    tRepository.deleteById(uuid);
	    return "deleted";
	}

	@Override
	public DeviceTelemetry updateByUuid(UUID uuid, DeviceTelemetry devicetelemetry) {
		Optional<DeviceTelemetry> Dit=tRepository.findById(uuid);
		DeviceTelemetry dt= Dit.get();
		dt.setData(devicetelemetry.getData());
		return tRepository.save(dt);
	}
	
	 @Override
	    public Optional<Device> findByMacAddress(String macAddress) {
	        return tRepository.findByMacAddress(macAddress);
	    }
}

