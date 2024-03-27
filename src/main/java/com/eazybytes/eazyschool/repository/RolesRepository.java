package com.eazybytes.eazyschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.eazyschool.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>{
	
	
	Roles getByRoleName(String roleName);
}
