package com.PatientMonitoringPlatform.config;

import java.util.Optional;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.PatientMonitoringPlatform.model.Device;
import com.PatientMonitoringPlatform.model.DeviceTelemetry;
import com.PatientMonitoringPlatform.repository.DeviceRepository;
import com.PatientMonitoringPlatform.repository.DeviceTelemetryRepository;
import com.PatientMonitoringPlatform.service.DeviceTelemetryService;
@Component
public class OnMessageCallback implements MqttCallback {
	

	@Autowired
    private DeviceTelemetryRepository tRepository;
	
	@Autowired
	private DeviceTelemetryService tService;

    @Autowired
    private DeviceRepository dRepository;
    
    
	public void connectionLost(Throwable cause) {
	        // After the connection is lost, it usually reconnects here
	   System.out.println("disconnect, you can reconnect");
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
	    System.out.println("Received message topic: " + topic);
	    System.out.println("Received message content: " + new String(message.getPayload()));

	    try {
	        // Extract MAC address from topic (e.g., "00:1A:2B:3C:4D:5E/telemetry")
	        String[] parts = topic.split("/");
	        String macAddress = parts[0];

	        Optional<Device> deviceOptional = dRepository.findByMacAddress(macAddress);
	        if (deviceOptional.isEmpty()) {
	            System.out.println("❌ No device found with MAC address: " + macAddress);
	            return;
	        }

	        Device device = deviceOptional.get();

	        // Get the payload as JSON string
	        String payload = new String(message.getPayload());

	        // Create DeviceTelemetry and save to DB
	        DeviceTelemetry telemetry = new DeviceTelemetry(device, payload);
	        tRepository.save(telemetry);

	        System.out.println("✅ Telemetry saved to DB for device: " + macAddress);

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("❌ Error while processing MQTT message: " + e.getMessage());
	    }
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		 System.out.println("deliveryComplete: " + token.isComplete());
		
		
	}

}

