package com.PatientMonitoringPlatform.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PatientMonitoringPlatform.model.Role;
import com.PatientMonitoringPlatform.repository.RoleRepository;

@Service

public class RoleServiceImpl implements RoleService                                                                                         {
	@Autowired
	private RoleRepository rRepository;
	
	@Override
	public Role createRole(Role role) {
		return rRepository.save(role);
	}

	@Override
	public Optional<Role> getRoleByUuid(UUID uuid) {
		return rRepository.findById(uuid);
	}

	@Override
	public List<Role> getAllRole() {
		return rRepository.findAll();
	}

	@Override
	public String deleteByUuid(UUID uuid) {
		rRepository.deleteById(uuid);
		return "delete by id successfully";
	}

	@Override
	public Role updateByUuid(UUID uuid, Role role) {
		Optional<Role>r1=rRepository.findById(uuid);
		Role r = r1.get();
		r.setName(role.getName());
		return rRepository.save(r) ;
	}

}
