package com.PatientMonitoringPlatform.model;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonRawValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="devicetelemetry")


public class DeviceTelemetry {
	@Id
	@GeneratedValue(strategy= GenerationType.UUID)
	private UUID id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;
	
	@JsonRawValue
	@Column()
	private String data;
	
	@Column(unique = true)
	private String macAddress;
	
	
	@CreationTimestamp
	private Date createdAt; 

	
	@UpdateTimestamp
	private Date updatedAt;
	
	
	public DeviceTelemetry(Device device, String data) {
        this.device = device;
        this.data = data;
        
    }


	public DeviceTelemetry() {
		// TODO Auto-generated constructor stub
	}
}
