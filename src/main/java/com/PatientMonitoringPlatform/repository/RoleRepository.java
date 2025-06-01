package com.PatientMonitoringPlatform.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PatientMonitoringPlatform.model.Role;



public interface RoleRepository extends JpaRepository<Role, UUID>  {

}
