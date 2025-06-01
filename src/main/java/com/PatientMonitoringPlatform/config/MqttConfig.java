package com.PatientMonitoringPlatform.config;

import java.util.List;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.PatientMonitoringPlatform.model.Device;
import com.PatientMonitoringPlatform.repository.DeviceRepository;

@Configuration
public class MqttConfig {

    
    private static final int QOS = 2;
    private static final String BROKER = "tcp://15.206.165.113:1883";
    private static final String CLIENT_ID = "emqx_test";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "public";
    @Autowired
    private  DeviceRepository dRepository ;
    
    
    @Autowired
    private OnMessageCallback onMessageCallback; 

    @Bean
    public MqttClient mqttClient() {
        try {
            MqttClient client = new MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());

            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(USERNAME);
            connOpts.setPassword(PASSWORD.toCharArray());
            connOpts.setCleanSession(true);

            client.setCallback(new OnMessageCallback());

            System.out.println("Connecting to broker: " + BROKER);
            client.connect(connOpts);
            System.out.println("Connected to MQTT broker");

            // Subscribe to topic
            
            List<Device> devices = dRepository.findAll();
            
            for (int i = 0; i < devices.size(); i++) {
                Device device = devices.get(i); 
                String macAddress = device.getMacAddress();
                String subscribeTopic =  macAddress + "/telemetry";

                client.subscribe(subscribeTopic, QOS);
                System.out.println("Subscribed to topic: " + subscribeTopic);
            }

            


            return client;
        } catch (MqttException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to MQTT broker", e);
        }
    }
}

