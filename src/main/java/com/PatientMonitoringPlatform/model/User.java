package com.PatientMonitoringPlatform.model;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false,unique=false)
	private String name;
	
	@Column(nullable = false,unique=true)
	private String email;
	
	@Column(nullable=false,unique=true)
	private Long phone_no;
	
	@Column(nullable=false,unique=true)
	private String password;
	
	@Column(nullable=true,unique=false)
	private String complete_address;
	
	@Column(nullable=false,unique=false)
	private String district;
	
	@Column(nullable=false,unique=false)
	private String state;
	
	@Column(nullable=false,unique=false)
	private String country;
	
	@Column(nullable=false,unique=true)
	private String pincode;
	
	@CreationTimestamp
	@Column(nullable=false,updatable=false)
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(nullable=false)
	private Date updatedAt;
	
	@ManyToOne
	private Role role;
	

}
