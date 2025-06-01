package com.PatientMonitoringPlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PatientMonitoringPlatform.model.Device;
import com.PatientMonitoringPlatform.repository.DeviceRepository;



@Service

public class DeviceServiceImpl implements DeviceService{
	@Autowired
    private DeviceRepository dRepository;

    @Override
    public Device createDevice(Device device) {
        return dRepository.save(device);
    }

    @Override
    public Optional<Device> getDeviceByUuid(UUID uuid) {
        return dRepository.findById(uuid);
    }

    @Override
    public List<Device> getAllDevices() {
        return dRepository.findAll();
    }

    @Override
    public String deleteByUuid(UUID uuid) {
    	dRepository.deleteById(uuid);
        return "Device deleted successfully";
    }

    @Override
    public Device updateByUuid(UUID uuid, Device device) {
        Optional<Device> Div = dRepository.findById(uuid);
        Device d = Div.get();
        d.setDeviceName(device.getDeviceName());
        d.setMacAddress(device.getMacAddress());
        d.setPowerMode(device.getPowerMode());
        d.setRelationshipWithUser(device.getRelationshipWithUser());
        d.setDeviceState(device.getDeviceState());
        d.setCommunicationType(device.getCommunicationType());
        d.setDeviceType(device.getDeviceType());
        d.setUpdatedAt(device.getUpdatedAt());
        return dRepository.save(d);
        
    }
}


