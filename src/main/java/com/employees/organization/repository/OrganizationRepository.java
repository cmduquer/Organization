package com.employees.organization.repository;

import java.util.List;

import com.employees.organization.model.Organization;

public interface OrganizationRepository {
	
	public Organization add(Organization organization) ;
	
	public Organization findById(Long id) ;
	
	public List<Organization> findAll() ;

}
