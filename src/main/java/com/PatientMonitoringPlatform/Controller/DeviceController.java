package com.PatientMonitoringPlatform.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PatientMonitoringPlatform.model.Device;
import com.PatientMonitoringPlatform.service.DeviceService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/devices")


public class DeviceController {
	@Autowired
	private DeviceService dService;
	
	@PostMapping
	  public Device createDevice(@RequestBody Device device) {
		 return dService.createDevice(device);
	}
	
	@GetMapping("/{uuid}")
    public Optional<Device> getDeviceByUuid(@PathVariable String uuid) {
        return dService.getDeviceByUuid(UUID.fromString(uuid));
    }
	
	
	@GetMapping
	public List<Device> getAllDevices() {
	    List<Device> devices = dService.getAllDevices();
	    System.out.println("Fetched Devices: " + devices);
	    return devices;
	}

	 
	 @DeleteMapping("/{uuid}")
	 public String deleteByUuid(@PathVariable String uuid) {
		 return dService.deleteByUuid(UUID.fromString(uuid));
	}
	 
	 @PutMapping("/{uuid}")
	 public Device updateByUuid(@PathVariable String uuid, @RequestBody Device device) {
		 return dService.updateByUuid(UUID.fromString(uuid), device);
	}
	
}



